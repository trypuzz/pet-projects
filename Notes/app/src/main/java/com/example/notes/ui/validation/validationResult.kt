package com.example.notes

sealed class ValidationResult
object Valid : ValidationResult()
class   Invalid(val textError: Int) : ValidationResult()