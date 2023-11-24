package com.example.noteapp.DataFlow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.screens.DummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteView @Inject constructor(private var repository: Repository) :ViewModel() {
    private var _noteList = MutableStateFlow<List<NoteData>>(emptyList())
    var noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){ repository.getData().distinctUntilChanged()
            .collect{
            if (it.isEmpty())
            {
                Log.d("viewInit","NoteData is empty")
            }
            else
            {
                _noteList.value = it
            } } }
         }
    fun noteAdd(note: NoteData) = viewModelScope.launch {repository.addNote(note)}

    fun noteRemove(note: NoteData) = viewModelScope.launch { repository.delete(note) }

    fun noteUpdate(note: NoteData) = viewModelScope.launch { repository.updateNote(note) }


}