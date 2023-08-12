package com.example.assignment2

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.ChatDetailScreen.route + "/{chatID}", arguments = listOf(
            navArgument("chatID") {
                type = NavType.StringType
            }
        )) {
            ChatDetailScreen(
                navController = navController,
                chatID = it.arguments?.getString("chatID"),
            )
        }
    }
}