package com.example.notes.ui.screeens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.Task
import com.example.notes.repositories.TaskRepository
import com.example.notes.utils.toListTask
import kotlinx.coroutines.launch


class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    val listTaskVM = MutableLiveData<ArrayList<Task>>()

    fun getTask() {
        viewModelScope.launch {
            listTaskVM.value = repository.getListTasks().toListTask()
        }
    }

    var listsSearchTask = arrayListOf<Task>()

    fun searchTask(searchTask: String) {
            listTaskVM.value = listsSearchTask.filter {
                it.nameTask.contains(searchTask) || it.messageTask.contains(searchTask)
            } as ArrayList<Task>
    }
}