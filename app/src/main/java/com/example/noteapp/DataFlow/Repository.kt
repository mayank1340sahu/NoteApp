package com.example.noteapp.DataFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class Repository @Inject constructor(private val NoteDao: NoteDao){

    fun getData():Flow<List<NoteData>> = NoteDao.getNote().flowOn(Dispatchers.IO).conflate()
    suspend fun addNote(noteData: NoteData) = NoteDao.loadNote(noteData)
    suspend fun updateNote(noteData: NoteData) = NoteDao.updateNote(noteData)
   suspend fun getNoteById(id: UUID) = NoteDao.getNoteById(id)
    suspend fun delete(id: UUID) = NoteDao.deleteNote(id)
    suspend fun deleteAll() = NoteDao.deleteAll()
}