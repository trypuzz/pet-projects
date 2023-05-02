package com.example.pokeapi_list.repositories

import android.util.Log
import androidx.constraintlayout.helper.widget.Flow
import androidx.paging.*
import com.squareup.moshi.Json
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://pokeapi.co/api/v2/"
const val LIMIT = 20


class PokemonRepository(private val pokemonApi : PokemonApi) {
    fun getPokemonList() = Pager(
        pagingSourceFactory = {PokemonPagingSource(pokemonApi)},
        config = PagingConfig(pageSize = LIMIT)
    ).flow

    suspend fun getSinglePokemon(pokemonName : String) : SinglePokemon {
        return pokemonApi.getSinglePokemon(pokemonName)
    }
}


class PokemonPagingSource(private val api : PokemonApi) : PagingSource<Int, PokemonListItem>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItem> {
        return try {
            val offset = params.key ?: 0
            val response = api.getPokemonList(offset, LIMIT)

            Log.d("response", response.toString())

            LoadResult.Page(
                data = response.results,
                prevKey = if (offset == 0) null else offset - LIMIT,
                nextKey = if (response.results.isEmpty()) null else offset + LIMIT
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}



interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset : Int,
        @Query("limit") limit : Int
    ) : PokemonListResult

    @GET("pokemon/{pokemonName}/")
    suspend fun getSinglePokemon(
        @Path("pokemonName") pokemonName : String
    ) : SinglePokemon
}

data class PokemonListResult(
    @field:Json(name = "count") val count : Int,
    @field:Json(name = "next") val next : String?,
    @field:Json(name = "previous") val previous : String?,
    @field:Json(name = "result") val results : List<PokemonListItem>
)

data class PokemonListItem(
    @field:Json(name = "name") val name : String,
    @field:Json(name = "url") val url : String
)

data class SinglePokemon(
    @field:Json(name = "name") val name : String?,
    @field:Json(name = "types") val types : List<PokemonType>?,
    @field:Json(name = "weight") val weight : Int?,
    @field:Json(name = "height") val height : Int?,
    @field:Json(name = "sprites") val sprites : Sprites?
)

data class PokemonType(
    @field:Json(name = "type") val type : Type?
)

data class Type(
    @field:Json(name = "name") val name : String?
)

data class Sprites(
    @field:Json(name = "front_default") val front_default : String?
)