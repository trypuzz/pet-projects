package com.example.notes.util

import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

private const val SIMPLE_DATE_FORMAT = "dd.mm.yyyy"

fun DatePicker.getData(): String {
    val calendar = Calendar.getInstance()
    calendar[year, month] = dayOfMonth
    val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT)
    return dateFormat.format(calendar.time)
}
