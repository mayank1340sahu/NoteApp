package com.example.noteapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.DataFlow.NoteData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(note:NoteData,paddingValues: PaddingValues,onClick:()->Unit) {
    Card(onClick = {onClick()}, modifier = Modifier
        .padding(paddingValues)
        .height(50.dp)
        .width(100.dp)){
        val title = note.title
        val text = note.note
        Text(text = title)
        Text(text = text)
        Text(text = "${note.date}")
    }
}
