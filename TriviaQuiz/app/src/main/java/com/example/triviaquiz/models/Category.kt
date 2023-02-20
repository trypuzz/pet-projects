package com.example.triviaquiz.models

data class Category (
    val categoryName: String
) {
    override fun toString(): String {
        return categoryName.split(":")[1]
    }
}