package com.example.noteapp.navigaton

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.DataFlow.NoteView
import com.example.noteapp.screens.HomeScreen
import com.example.noteapp.screens.NoteScreen

@Composable
fun NoteNavigation(view: NoteView) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = EnumScreens.HomeScreen.name){
        composable( EnumScreens.HomeScreen.name){
                HomeScreen(view,navController)
        }
        composable(EnumScreens.NoteScreen.name+"/{id}/{note}/{title}",
        arguments = listOf(navArgument("note"){type = NavType.StringType},
                            navArgument("title"){type = NavType.StringType},
                            navArgument("id"){type = NavType.StringType}
        )
        ){
            val n = it.arguments?.getString("note")
            val m = it.arguments?.getString("title")
            val v = it.arguments?.getString("id")
            NoteScreen(view,navController,v,n,m)
        }
    }
}
