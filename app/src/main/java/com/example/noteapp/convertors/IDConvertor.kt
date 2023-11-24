package com.example.noteapp.convertors

import androidx.room.TypeConverter
import java.util.UUID

class IDConvertor {
    @TypeConverter
    fun uUIDToString(uuid: UUID): String {
        return uuid.toString()
    }
    @TypeConverter
    fun stringToUUID(string: String): UUID {
        return UUID.fromString(string)
    }
}