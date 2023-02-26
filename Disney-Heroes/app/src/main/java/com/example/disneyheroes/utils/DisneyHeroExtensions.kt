package com.example.disneyheroes.utils


import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.models.InfoHero
import com.example.disneyheroes.network.models.DisneyHeroResponse

//fun DisneyHeroResponse.toModel(): DisneyHero {
//    return DisneyHero(
//        id = this.id ?: 0,
//        name = this.name ?: "",
//        imageUrl = this.imageUrl ?: "",
//        listInfo = emptyList()
//    )
//}

fun DisneyHeroResponse.toDisneyHero(): DisneyHero {
    val list = arrayListOf<InfoHero>()

    if (allies?.isNotEmpty() == true) {
        list.add(InfoHero("Allies", allies))
    }
    if (enemies?.isNotEmpty() == true) {
        list.add(InfoHero("Enemies", enemies))
    }
    if (films?.isNotEmpty() == true) {
        list.add(InfoHero("Films", films))
    }
    if (parkAttractions?.isNotEmpty() == true) {
        list.add(InfoHero("Park attractions", parkAttractions))
    }
    if (shortFilms?.isNotEmpty() == true) {
        list.add(InfoHero("Short films", shortFilms))
    }
    if (tvShows?.isNotEmpty() == true) {
        list.add(InfoHero("TV shows", tvShows))
    }
    if (videoGames?.isNotEmpty() == true) {
        list.add(InfoHero("Video games", videoGames))
    }

    return DisneyHero(id = id, imageUrl = imageUrl ?: "", name = name ?: "", listInfo = list, isFavorite = false)
}

fun List<DisneyHeroResponse>.toListDisneyHero(): List<DisneyHero> =
    this.map { model -> model.toDisneyHero() }

