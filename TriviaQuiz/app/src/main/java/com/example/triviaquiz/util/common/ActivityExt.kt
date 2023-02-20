package com.example.triviaquiz.util.common

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.triviaquiz.models.Answer

fun Activity.dismissKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

fun <E> MutableList<E>.updated(index: Int, elem: E) =
    mapIndexed { i, existing -> if (i == index) elem else existing }

fun List<String>.transformToAnswer(): List<Answer>{
    return map { Answer(it, false) }
}
