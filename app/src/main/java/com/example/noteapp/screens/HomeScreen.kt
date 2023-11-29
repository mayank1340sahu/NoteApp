package com.example.noteapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.DataFlow.NoteView
import com.example.noteapp.navigaton.EnumScreens
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NoteView = viewModel(),
    navController: NavController,
) {
    val notes = viewModel.noteList.collectAsState().value
    Scaffold(Modifier.fillMaxSize(),topBar = {  TopAppBar(modifier = Modifier
        .padding(3.dp)
        .height(34.dp),
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFF56F45)),
            title = {
                Text(
                    color = Color.White,
                    text = "JetNote",
                    style = MaterialTheme.typography.headlineMedium,
                )
                     }
        )
                          },
            floatingActionButton = { FloatingActionButton(modifier = Modifier.padding(),
                onClick = {
                    val g = " "
                    val h = " "
                    val r = UUID.randomUUID().toString()
                    navController.navigate(EnumScreens.NoteScreen.name+"/${r}/${g}/${h}") }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Notes")
        }
                                   },
            floatingActionButtonPosition = FabPosition.End,
            containerColor = Color(0xFF494948)
        ) { values ->

                    LazyColumn(Modifier.fillMaxSize()) {
                           items(notes) {
                              NoteCard(note = it, paddingValues = values) {
                                  val r = it.id
                                  val g = it.title
                                  val h = it.note
                                  navController.navigate(EnumScreens.NoteScreen.name+"/${r}/${g}/${h}")
                              }
                               }
                        }
        }
        }


@Preview
@Composable
fun Gh() {
    val nav = rememberNavController()
    HomeScreen(navController = nav)
}