package com.example.pokeapi_list.repositories

import android.util.Log
import androidx.paging.*
import javax.inject.Inject

const val BASE_URL = "https://pokeapi.co/api/v2/"
const val LIMIT = 50


interface PokemonRepository {
    fun getPokemonList(): kotlinx.coroutines.flow.Flow<PagingData<PokemonListItem>>
    suspend fun getSinglePokemon(pokemonName: String): SinglePokemon
}

class PokemonRepositoryImpl @Inject constructor(private val pokemonApi: PokemonApi) :
    PokemonRepository {

    override fun getPokemonList() = Pager(
        pagingSourceFactory = { PokemonPagingSource(pokemonApi) },
        config = PagingConfig(pageSize = LIMIT)
    ).flow

    override suspend fun getSinglePokemon(pokemonName: String): SinglePokemon {
        return pokemonApi.getSinglePokemon(pokemonName)
    }
}

class PokemonPagingSource @Inject constructor(private val api: PokemonApi) :
    PagingSource<Int, PokemonListItem>() {
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