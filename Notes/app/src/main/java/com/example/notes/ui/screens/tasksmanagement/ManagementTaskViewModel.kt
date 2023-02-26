package com.example.notes.ui.screeens.taskmanagement

import androidx.lifecycle.ViewModel
import com.example.notes.model.TaskEntity
import com.example.notes.repositories.TaskRepository

class ManagementTaskViewModel : ViewModel() {

    private val repository = TaskRepository()
    var taskAdded: (() -> Unit)? = null

    suspend fun addTaskVM(
        taskName: String, taskMessage: String, taskDate: String, userEmail: String
    ) {
        repository.addTasks(TaskEntity(0, taskName, taskMessage, taskDate, userEmail))
        taskAdded?.invoke()
    }

    suspend fun deleteAllTask() {
        repository.deleteAllListTasks()
    }

    suspend fun deleteTask(id: Int) {
        repository.deleteTask(id)
    }
}