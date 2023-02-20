package com.example.triviaquiz.di.questiontrivia

import com.example.triviaquiz.network.QuestionTriviaApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object QuestionTriviaModule {
    @Provides
    @JvmStatic
    fun provideQuestionTriviaApi(retrofit: Retrofit): QuestionTriviaApi {
        return retrofit.create(QuestionTriviaApi::class.java)
    }
}