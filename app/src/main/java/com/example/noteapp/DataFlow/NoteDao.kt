package com.example.noteapp.DataFlow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDao {
    @Query("select * from NoteData")
    fun getNote(): Flow<List<NoteData>>

    @Query("select * from  NoteData where id =:id")
   suspend fun getNoteById(id:UUID): NoteData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun loadNote(noteData: NoteData)

    @Update
   suspend fun updateNote(noteData: NoteData)

   @Delete
   suspend fun deleteNote(noteData: NoteData)

    @Query("delete from NoteData")
   suspend fun deleteAll()
}