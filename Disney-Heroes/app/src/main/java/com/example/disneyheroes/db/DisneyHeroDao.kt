package com.example.disneyheroes.db

import androidx.room.*
import com.example.disneyheroes.models.DisneyHero

@Dao
interface DisneyHeroDao {

    @Query("SELECT * FROM disney_hero_DB")
    suspend fun getFavoriteDisneyHeroes(): List<DisneyHero>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHero(hero: DisneyHero)

    @Delete
    suspend fun deleteHero(hero: DisneyHero)
}