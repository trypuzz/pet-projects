package com.example.pokeapi_list.ui.list

import com.example.pokeapi_list.repositories.PokemonListItem

class PokemonListener(val clickListener: (pokemon: PokemonListItem) -> Unit) {
    fun onClick(pokemon: PokemonListItem) = clickListener(pokemon)
}