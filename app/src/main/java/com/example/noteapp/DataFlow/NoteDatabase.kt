package com.example.noteapp.DataFlow

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.convertors.DateTypeConverter
import com.example.noteapp.convertors.IDConvertor

@Database(entities = [NoteData::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class,IDConvertor::class)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun getDao(): NoteDao
}
