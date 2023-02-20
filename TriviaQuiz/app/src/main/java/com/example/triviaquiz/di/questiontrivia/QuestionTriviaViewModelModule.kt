package com.example.triviaquiz.di.questiontrivia

import androidx.lifecycle.ViewModel
import com.example.gitpocket.di.ViewModelKey
import com.example.triviaquiz.ui.mainmenu.QuestionTriviaViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class QuestionTriviaViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuestionTriviaViewModel::class)
    abstract fun bindQuestionTriviaViewModel(
        questionTriviaViewModel: QuestionTriviaViewModel
    ): ViewModel
}