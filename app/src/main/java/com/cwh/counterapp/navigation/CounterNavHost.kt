package com.cwh.counterapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cwh.counterapp.ui.screen.CounterScreen
import com.cwh.counterapp.ui.screen.HistoryScreen
import com.cwh.counterapp.ui.screen.HomeScreen

@Composable
fun CounterNavHost(
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(
                onStartTasbih = {
                    navController.navigate(
                        Screen.Counter.route
                    )
                }
            )
        }

        composable(Screen.Counter.route) {

            CounterScreen()
        }

        composable(Screen.History.route) {

            HistoryScreen()
        }
    }
}