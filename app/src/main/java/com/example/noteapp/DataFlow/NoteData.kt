package com.example.noteapp.DataFlow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.Instant
import java.util.UUID

@Entity
data class NoteData(

    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo
    val title:String,

    @ColumnInfo
    val note:String,

    @ColumnInfo
    var date: java.util.Date = Date.from(Instant.now())
)
