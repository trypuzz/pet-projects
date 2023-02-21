package com.example.disneyheroes.network.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "disney_hero_network")
data class DisneyHeroResponse(
    @PrimaryKey
    @SerializedName("_id")
    val id: Int,
    @ColumnInfo(name = "allies")
    val allies: ArrayList<String>?,
    @ColumnInfo(name = "enemies")
    val enemies: ArrayList<String>?,
    @ColumnInfo(name = "films")
    val films: ArrayList<String>?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "parkAttractions")
    val parkAttractions: ArrayList<String>?,
    @ColumnInfo(name = "shortFilms")
    val shortFilms: ArrayList<String>?,
    @ColumnInfo(name = "tvShows")
    val tvShows: ArrayList<String>?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "videoGames")
    val videoGames: ArrayList<String>?
)
