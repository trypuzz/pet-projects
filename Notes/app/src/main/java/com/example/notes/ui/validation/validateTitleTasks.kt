package com.example.notes

fun validateTitleTasks(task : String) : ValidationResult {
    return when {
        task.isBlank() -> {
            Invalid(R.string.taskName)
        }
        else -> Valid
    }
}