package com.example.disneyheroes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "disney_hero_DB")
data class DisneyHero(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @TypeConverters
    @ColumnInfo(name = "listInfo")
    val listInfo: ArrayList<InfoHero>,
    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean
)

data class InfoHero(
    val category: String,
    val list: ArrayList<String>
)