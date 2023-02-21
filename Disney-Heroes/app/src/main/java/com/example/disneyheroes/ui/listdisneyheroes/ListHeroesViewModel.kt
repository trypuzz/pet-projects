package com.example.disneyheroes.ui.listdisneyheroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.repositories.DisneyHeroesDataSource
import com.example.disneyheroes.repositories.DisneyHeroesRepository
import com.example.disneyheroes.utils.toListDisneyHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHeroesViewModel @Inject constructor(
    private val repository: DisneyHeroesRepository,
    private val dataSource: DisneyHeroesDataSource
) : ViewModel() {

    private val _listHeroes = MutableLiveData<List<DisneyHero>>()

    private val _listFavoriteHeroes = MutableLiveData<List<DisneyHero>>()
    val listFavoriteHeroes: LiveData<List<DisneyHero>> = _listFavoriteHeroes

    var onError: (() -> Unit)? = null

    val flowListHeroes = Pager(
        PagingConfig(pageSize = 5)
    ) {
        dataSource
    }.flow
        .cachedIn(viewModelScope)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        onError?.invoke()
    }

    fun getListHeroes() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getDisneyHeroes(1, 5)
            if (response.isSuccessful) {
                _listHeroes.postValue(
                    response.body()?.data?.toListDisneyHero() ?: emptyList()
                )
            } else {
                onError?.invoke()
            }
        }
    }

    fun getListFavoriteHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _listFavoriteHeroes.postValue(repository.getFavoriteDisneyHeroes())
        }
    }
}