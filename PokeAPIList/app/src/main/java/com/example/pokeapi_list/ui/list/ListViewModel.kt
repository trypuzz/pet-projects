package com.example.pokeapi_list.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokeapi_list.repositories.*
import kotlinx.coroutines.launch
import com.example.pokeapi_list.domain.usecases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    lateinit var pokemons: LiveData<PagingData<PokemonListItem>>

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            pokemons = getPokemonListUseCase().cachedIn(viewModelScope)
        }
    }
}