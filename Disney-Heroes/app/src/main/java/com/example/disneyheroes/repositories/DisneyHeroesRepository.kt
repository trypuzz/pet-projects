package com.example.disneyheroes.repositories

import com.example.disneyheroes.db.DisneyHeroDao
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.network.DisneyHeroesApi
import com.example.disneyheroes.network.models.AllDisneyHeroesResponse
import com.example.disneyheroes.network.models.DisneyHeroResponse
import retrofit2.Response
import javax.inject.Inject

class DisneyHeroesRepository @Inject constructor(
    private val api: DisneyHeroesApi,
    private val heroDao: DisneyHeroDao
) {

    suspend fun getDisneyHeroes(page: Int, limit: Int): Response<AllDisneyHeroesResponse> {
        return api.getDisneyHeroes(page, limit)
    }

    suspend fun getImageDisneyHero(id: String): Response<DisneyHeroResponse> {
        return api.getImageDisneyHero(id)
    }

    suspend fun getFavoriteDisneyHeroes(): List<DisneyHero> {
        return heroDao.getFavoriteDisneyHeroes()
    }

    suspend fun addHeroToFavorite(hero: DisneyHero) {
        heroDao.addHero(hero)
    }

    suspend fun deleteHeroFromFavorite(hero: DisneyHero) {
        heroDao.deleteHero(hero)
    }
}