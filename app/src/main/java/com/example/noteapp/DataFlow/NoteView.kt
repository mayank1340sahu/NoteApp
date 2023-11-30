package com.example.noteapp.DataFlow

import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var not = mutableStateOf(NoteData(title = "", note = ""))
     var showDialog by mutableStateOf(false)
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
    // Function to show the dialog
    fun showDialog() {
        showDialog = true
    }
    // Function to hide the dialog
    fun hideDialog() {
        showDialog = false
    }

    fun assignNote(note:NoteData){
        not.value = note
    }
    fun noteAdd(note: NoteData) = viewModelScope.launch {repository.addNote(note)}

    fun noteRemove(id: UUID) = viewModelScope.launch { repository.delete(id) }

    fun noteUpdate(note: NoteData) = viewModelScope.launch { repository.updateNote(note) }

    fun getNoteById(id : UUID): NoteData? { var note:NoteData? = null
        viewModelScope.launch{
            note = repository.getNoteById(id)
        }
        return note
    }

    fun deleteAll() = viewModelScope.launch { repository.deleteAll() }
}