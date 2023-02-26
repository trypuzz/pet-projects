package com.example.notes

import com.example.notes.constance.Constance

fun validatePasswords(password : String) : ValidationResult {
    return when {
        password.isBlank() -> {
            Invalid(R.string.password_blank)
        }
        password.length < Constance.MIN_PASS_TEXT -> {
            Invalid(R.string.password_min_size)
        }
        password.length > Constance.MAX_PASS_TEXT -> {
            Invalid(R.string.password_max_size)
        }
        !password.matches(".*[a-z].*".toRegex()) -> {
            Invalid(R.string.password_lower_case_symbol)
        }
        !password.matches(".*[A-Z].*".toRegex()) -> {
            Invalid(R.string.password_upper_case_symbol)
        }
        !password.matches(".*[@#\$%^&+=].*".toRegex()) -> {
            Invalid(R.string.password_spec_symbol)
        }
        !password.matches(".*[0-9].*".toRegex()) -> {
            Invalid(R.string.password_number)
        }
        else -> Valid
    }
}