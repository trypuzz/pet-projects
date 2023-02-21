package com.example.notes.ui.taskmanagement

import androidx.lifecycle.ViewModel
import com.example.notes.repositories.TaskRepository

class DeleteTaskViewModel: ViewModel() {
    private val repository = TaskRepository()

    var taskDeleted: (() -> Unit)? = null

}
