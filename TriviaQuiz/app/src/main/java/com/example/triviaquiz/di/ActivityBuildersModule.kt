package com.example.triviaquiz.di

import com.example.triviaquiz.di.questiontrivia.QuestionTriviaModule
import com.example.triviaquiz.di.questiontrivia.QuestionTriviaViewModelModule
import com.example.triviaquiz.ui.mainmenu.MainMenuActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            QuestionTriviaViewModelModule::class,
            QuestionTriviaModule::class
        ]
    )
    abstract fun contributeMainMenuActivity(): MainMenuActivity

}