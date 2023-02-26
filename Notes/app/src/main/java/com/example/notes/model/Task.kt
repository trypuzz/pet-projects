package com.example.notes.model
import java.io.Serializable

data class Task(
    val id: Int,
    val nameTask: String,
    val messageTask: String,
    val data: String,
    val userEmail: String
) : Serializable