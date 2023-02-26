package com.example.disneyheroes.ui.disneyhero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.repositories.DisneyHeroesRepository
import com.example.disneyheroes.repositories.SharedPreferencesRepository
import com.example.disneyheroes.utils.toDisneyHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: DisneyHeroesRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    private val _oneHero = MutableLiveData<DisneyHero>()
    val oneHero: LiveData<DisneyHero> = _oneHero

    private val isFavoriteHero = MutableLiveData(false)

    fun getInfoOneHero(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImageDisneyHero(id)
            if (response.isSuccessful) {
                _oneHero.postValue((response.body()?.toDisneyHero()))
            } else {
                response.errorBody()
            }
        }
    }

    private fun addFavoriteHero(hero: DisneyHero) {
        viewModelScope.launch(Dispatchers.IO) {
            hero.isFavorite = true
            repository.addHeroToFavorite(hero)
        }
    }

    private fun deleteFavoriteHero(hero: DisneyHero) {
        viewModelScope.launch(Dispatchers.IO) {
            hero.isFavorite = false
            repository.deleteHeroFromFavorite(hero)
        }
    }

    fun setFavoriteHero(key: String, value: Boolean) =
        sharedPreferencesRepository.setFavoriteHero(key, value)

    fun getFavoriteHero(key: String) = sharedPreferencesRepository.getFavoriteHero(key)

    fun selectFavoriteHero(hero: DisneyHero) {
        if (isFavoriteHero.value == true) {
            deleteFavoriteHero(hero)
        } else {
            addFavoriteHero(hero)
        }
        isFavoriteHero.value = !(isFavoriteHero.value ?: true)
    }
}