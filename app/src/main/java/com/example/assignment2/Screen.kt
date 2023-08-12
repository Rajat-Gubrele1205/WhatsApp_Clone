package com.example.assignment2


sealed class Screen(val route: String) {
    object MainScreen : Screen("home_screen")
    object ChatDetailScreen : Screen("chat_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
