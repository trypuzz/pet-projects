package com.example.notes

fun validateMessageTasks(message : String) : ValidationResult {
    return when {
        message.isBlank() -> {
            Invalid(R.string.message_task)
        }
        else -> Valid
    }
}