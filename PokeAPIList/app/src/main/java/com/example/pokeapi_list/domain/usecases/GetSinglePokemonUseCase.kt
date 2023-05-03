package com.example.pokeapi_list.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.pokeapi_list.repositories.PokemonRepository
import com.example.pokeapi_list.repositories.SinglePokemon
import javax.inject.Inject

class GetSinglePokemonUseCase @Inject constructor(private val repository : PokemonRepository) {

    operator fun invoke(pokemonName : String) : LiveData<SinglePokemon> {
        return liveData {
            emit(repository.getSinglePokemon(pokemonName))
        }
    }
}