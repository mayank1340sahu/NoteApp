package com.example.noteapp.convertors

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun longToDate(long: Long):Date {
        return Date(long)
    }
}