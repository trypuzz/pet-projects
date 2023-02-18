package com.example.notes.utils

import com.example.notes.model.Task
import com.example.notes.model.TaskEntity

fun TaskEntity.toTask(): Task =
    Task(id, nameTask, messageTask, data, userEmail)

fun ArrayList<TaskEntity>.toListTask(): ArrayList<Task> =
    this.map { it.toTask() } as ArrayList<Task>