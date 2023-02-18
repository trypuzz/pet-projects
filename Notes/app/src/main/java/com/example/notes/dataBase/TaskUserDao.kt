package com.example.notes.dataBase

import androidx.room.Dao
import androidx.room.Query
import com.example.notes.model.TaskEntity

@Dao
interface TaskUserDao {
    @Query("SELECT * FROM tasks ")
    fun getTaskByUser(): List<TaskEntity>
}