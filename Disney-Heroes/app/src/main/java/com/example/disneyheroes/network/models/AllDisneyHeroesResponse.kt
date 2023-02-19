package com.example.disneyheroes.network.models

data class AllDisneyHeroesResponse(
    val count: Int,
    val data: List<DisneyHeroResponse>
)
