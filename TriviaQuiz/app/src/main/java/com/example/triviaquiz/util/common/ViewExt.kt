package com.example.triviaquiz.util.common

import android.view.View

fun View.conditionalVisibility(condition: Boolean) {
    visibility = if (condition) View.VISIBLE
    else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.gone(condition: Boolean) {
    visibility = if(condition) View.GONE
    else View.VISIBLE
}