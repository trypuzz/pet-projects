package com.example.notes.dataBase

import com.example.notes.model.Task

object TaskRepository {

    private val taskListSingleton: ArrayList<Task> = ArrayList()

    fun addNewTask(task: Task){
        taskListSingleton.add(task)

    }

    fun getTasks(): List <Task>{
        return taskListSingleton
    }

}