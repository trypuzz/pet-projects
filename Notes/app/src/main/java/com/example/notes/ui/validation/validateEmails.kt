package com.example.notes

import android.util.Patterns

fun validateEmails(email : String) : ValidationResult {
    return when {
        email.isBlank() -> {
            Invalid(R.string.email_blank)
        }
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            Invalid(R.string.email_wrong_type)
        }
        else -> Valid
    }
}