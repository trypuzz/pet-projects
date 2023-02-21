package com.example.notes.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.model.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = (:id)")
    suspend fun deleteTask(id: Int)

    @Query("DELETE FROM tasks ")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM tasks")
    suspend fun selectAllTask(): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE userEmail LIKE :email")
    fun selectTaskByUser(email: String): List<TaskEntity>
}