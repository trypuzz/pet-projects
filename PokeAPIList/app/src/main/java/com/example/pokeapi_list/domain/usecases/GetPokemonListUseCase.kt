package com.example.pokeapi_list.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.pokeapi_list.repositories.PokemonListItem
import com.example.pokeapi_list.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository : PokemonRepository) {

    operator fun invoke(): LiveData<PagingData<PokemonListItem>> {
        return repository.getPokemonList().asLiveData()
    }

}