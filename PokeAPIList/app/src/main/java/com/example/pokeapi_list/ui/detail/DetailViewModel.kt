package com.example.pokeapi_list.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi_list.repositories.SinglePokemon
import com.example.pokeapi_list.domain.usecases.GetSinglePokemonUseCase
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getSinglePokemonUseCase: GetSinglePokemonUseCase): ViewModel() {

    lateinit var pokemon : LiveData<SinglePokemon>

    fun onPokemonOpened(pokemonName : String){
        viewModelScope.launch {
            pokemon = getSinglePokemonUseCase(pokemonName)
        }
    }
}