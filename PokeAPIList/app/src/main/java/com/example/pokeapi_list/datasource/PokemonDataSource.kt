package com.example.pokeapi_list.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokeapi_list.dataclasses.PokemonResult
import com.example.pokeapi_list.network.PokemonApi
import java.io.IOException

const val STARTING_OFFSET_INDEX = 0

class PokemonDataSource(private val pokemonApi: PokemonApi) :
    PagingSource<Int, PokemonResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val offset = params.key ?: STARTING_OFFSET_INDEX
        val loadSize = params.loadSize
        return try {
            val data = pokemonApi.getPokemons(loadSize, offset)
            val filteredData = data.results
            LoadResult.Page(
                data = filteredData,
                prevKey = if (offset == STARTING_OFFSET_INDEX) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } catch (t: Throwable) {
            var exception = t

            if (t is IOException) {
                exception = IOException("Check internet connection")
            }
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition
    }
}