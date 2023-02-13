package com.example.notes

object TaskRepository {

    private val taskListSingleton: ArrayList<Task> = ArrayList()

    fun addNewTask(task: Task){
        taskListSingleton.add(task)

    }

    fun getTasks(): List <Task>{
        return taskListSingleton
    }
}