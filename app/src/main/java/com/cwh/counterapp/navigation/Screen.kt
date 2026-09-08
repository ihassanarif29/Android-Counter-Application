package com.cwh.counterapp.navigation

sealed class Screen(
    val route: String
) {

    data object Home : Screen("home")

    data object Counter : Screen("counter")

    data object History : Screen("history")
}