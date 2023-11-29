package com.example.noteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.DataFlow.NoteData
import com.example.noteapp.DataFlow.NoteView
import com.example.noteapp.navigaton.EnumScreens
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NoteView,
    navController: NavController,
    v: String?,
    n: String?,
    m: String?
) {

    var note by rememberSaveable{
        mutableStateOf("")
    }
    var title by rememberSaveable {
        mutableStateOf("")
    }
    val id  by rememberSaveable {
        mutableStateOf(UUID.fromString(v))
    }
    if (n != null && m != null) {
        if (n != " " && m!= " ") {

            note = m
            title = n
        }
        }

    Scaffold(topBar = { TopAppBar(title = {},
        actions = {
            Button(onClick = {
                if ((title != "") && (note != "")) {
                    if (n == " ") {
                        viewModel.noteAdd(NoteData(title = title, note = note))
                        title = ""
                        note = ""
                    }
                    else
                    {
                        val not = NoteData(id,title,note)
                        viewModel.noteUpdate(not)
                    }
                    navController.popBackStack()
                }
            }) {
                Text(text = "Save")
            }

        }, colors = TopAppBarDefaults.mediumTopAppBarColors(
        Color(0xFFF56F45)
    ))

    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()) {

            BasicTextField(value = title,
                onValueChange ={title = it},
                Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next))


            BasicTextField(value =note,
                onValueChange = {note = it},
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
        }
    }
}