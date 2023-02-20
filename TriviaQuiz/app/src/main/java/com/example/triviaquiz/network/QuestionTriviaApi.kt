package com.example.triviaquiz.network

import com.example.triviaquiz.models.Questions
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionTriviaApi {
    @GET("/api.php?encode=url3986")
    fun retrieveQuestion(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): Single<Questions>
}