package com.example.disneyheroes.network

import com.example.disneyheroes.network.models.AllDisneyHeroesResponse
import com.example.disneyheroes.network.models.DisneyHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DisneyHeroesApi {

    @GET("/characters")
    suspend fun getDisneyHeroes(
        @Query("page") page: Int,
        @Query("limit") limit: Int? = 50
    ): Response<AllDisneyHeroesResponse>

    @GET("/characters/{id}")
    suspend fun getImageDisneyHero(@Path("id") id: String): Response<DisneyHeroResponse>
}