package com.example.pokeapi_list.di

import com.example.pokeapi_list.repositories.BASE_URL
import com.example.pokeapi_list.repositories.PokemonApi
import com.example.pokeapi_list.repositories.PokemonRepository
import com.example.pokeapi_list.repositories.PokemonRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Component(modules = [AppModule::class])
interface AppComponent {
    fun pokemonRepository(): PokemonRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun jsonAdapter(): Converter.Factory {
        return MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    }

    @Provides
    fun provideRepository(jsonAdapter: Converter.Factory): PokemonRepository {
        return PokemonRepositoryImpl(
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(jsonAdapter)
                .build()
                .create(PokemonApi::class.java)
        )
    }
}
