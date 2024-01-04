package com.example.noteapp.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.DataFlow.NoteData
import com.example.noteapp.DataFlow.NoteView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCard(note:NoteData,paddingValues: PaddingValues,view: NoteView,onClick:()->Unit) {
    val dialogState = view.showDialog
    Card(modifier = Modifier
        .padding(paddingValues)
        .height(70.dp)
        .fillMaxWidth()
        .combinedClickable(onClick = onClick, onLongClick = {
            view.assignNote(note)
            view.showDialog()
            Log.d("Go", "NoteCard: ${note.id}")
        })){
        val title = note.title
        val text = note.note
        val id = note.id
        Log.d("Go", "NoteCard: $id")

        Text(text = title)
        Text(text = text)
        Text(text = "${note.date}")

        if (dialogState)
        {
            DialogBox(onDismissRequest = {view.hideDialog()},
            onConfirmation = {
                view.hideDialog()
               view.noteRemove(view.not.value.id)
            },
                "Confirm",
                "this note will be deleted",
                Icons.Default.Delete)
        }
    }
}
