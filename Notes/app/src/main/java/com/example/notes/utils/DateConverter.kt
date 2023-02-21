package com.example.notes.utils

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateFromLong(value: Long): Date {
        return Date(value)
    }
    @TypeConverter
    fun longFromDate(date: Date): Long {
        return date.time
    }
}