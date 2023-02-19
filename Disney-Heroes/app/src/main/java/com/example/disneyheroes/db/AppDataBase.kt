package com.example.disneyheroes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.network.models.DisneyHeroResponse
import com.example.disneyheroes.utils.TypeConverter
import com.example.disneyheroes.utils.InfoHeroConverter

@Database(entities = [DisneyHeroResponse::class, DisneyHero::class], version = 1)
@TypeConverters(TypeConverter::class, InfoHeroConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun heroDao(): DisneyHeroDao
}