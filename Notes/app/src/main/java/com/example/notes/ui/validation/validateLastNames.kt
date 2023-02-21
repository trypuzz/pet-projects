package com.example.notes

fun validateLastNames (LastName : String) : ValidationResult {
    return when {
        LastName.isBlank() -> {
            Invalid(R.string.lastname_blank)
        }else -> Valid
    }
}