package com.example.pokeapi_list.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SinglePokemonResponse(
    val name: String,
    val types: String,
    val weight: Int,
    val height: Int,
    val sprite: String
) : Parcelable