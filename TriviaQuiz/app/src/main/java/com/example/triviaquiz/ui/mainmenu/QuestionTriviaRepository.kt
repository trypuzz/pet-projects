package com.example.triviaquiz.ui.mainmenu

import com.example.triviaquiz.models.Questions
import com.example.triviaquiz.network.QuestionTriviaApi
import com.example.triviaquiz.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuestionTriviaRepository @Inject constructor(
    private val questionTriviaApi: QuestionTriviaApi
) {
    fun retrieveQuestions(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<Questions>
    ) {
        questionTriviaApi.retrieveQuestion(amount, category, difficulty, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }
}