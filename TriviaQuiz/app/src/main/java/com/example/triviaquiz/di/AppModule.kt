package com.example.triviaquiz.di

import android.app.Application
import com.example.lifemarklibrary.Lifemark
import com.example.triviaquiz.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesLifemark(application: Application): Lifemark {
        return Lifemark(application)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesRetrofitInstance(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}