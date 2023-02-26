package com.example.notes

fun validateNames(name : String) : ValidationResult {
    return when {
        name.isBlank() -> {
            Invalid(R.string.name_blank)
        }else -> Valid
    }
}