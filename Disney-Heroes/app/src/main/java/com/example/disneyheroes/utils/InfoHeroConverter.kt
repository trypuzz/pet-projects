package com.example.disneyheroes.utils

import androidx.room.TypeConverter
import com.example.disneyheroes.models.InfoHero

class InfoHeroConverter {
    @TypeConverter
    fun fromStringToList(value: String): ArrayList<InfoHero> {
        return ArrayList()
    }

    @TypeConverter
    fun fromListToString(list: ArrayList<InfoHero>): String {
        return ""
    }
}