package com.example.notes

import android.app.Application
import com.example.notes.dataBase.TaskDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        TaskDataBase.initDB(applicationContext)
    }
}