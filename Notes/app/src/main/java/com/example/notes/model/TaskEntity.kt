package com.example.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tasks")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "nameTask")
    val nameTask: String,
    @ColumnInfo(name = "messageTask")
    val messageTask: String,
    @ColumnInfo(name = "data")
    val data: String,
    @ColumnInfo(name = "userEmail")
    val userEmail: String
): Serializable