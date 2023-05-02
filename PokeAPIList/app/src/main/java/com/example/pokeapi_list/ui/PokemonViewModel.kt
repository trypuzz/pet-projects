package com.example.pokeapi_list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi_list.network.Pokemon
import com.example.pokeapi_list.network.PokemonApi
import kotlinx.coroutines.launch

enum class PokemonApiStatus {LOADING, ERROR, DONE}

class PokemonViewModel : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon : LiveData<Pokemon> = _pokemon

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _status.value = PokemonApiStatus.LOADING
            try {
                _pokemons.value = PokemonApi.retrofitService.getData()
                _status.value = PokemonApiStatus.DONE
            } catch (e: Exception) {
                _pokemons.value = listOf()
                _status.value = PokemonApiStatus.ERROR
            }
        }
    }

    fun onPokemonClicked(pokemon: Pokemon) {
        _pokemon.value = pokemon
    }
}