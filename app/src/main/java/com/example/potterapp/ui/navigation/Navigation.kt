package com.example.potterapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.potterapp.ui.details.Details
import com.example.potterapp.ui.main.Main

@Composable
fun Navigation (modifier: Modifier) {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "main_page", modifier = modifier) {
        composable("main_page") {
            Main()
        }
        composable("details_page/{id}", arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        )) { backStackEntry ->
            Details(
                id = backStackEntry.arguments?.getString("id")
            )
        }
    }
}