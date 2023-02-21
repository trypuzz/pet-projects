package com.example.notes.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notes.model.TaskEntity
import com.example.notes.model.User
import com.example.notes.utils.DateConverter

@Database(entities = [TaskEntity::class, User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun userDao(): UserDao

    abstract fun taskUserDao(): TaskUserDao
}