package com.example.notes.dataBase

import android.content.Context
import androidx.room.Room


object TaskDataBase {

    lateinit var db: AppDataBase

    fun initDB(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}

