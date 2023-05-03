package com.example.pokeapi_list.repositories

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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