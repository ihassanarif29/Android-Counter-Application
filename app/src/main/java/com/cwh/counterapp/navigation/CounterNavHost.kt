package com.cwh.counterapp.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cwh.counterapp.ui.components.BottomNavigationBar
import com.cwh.counterapp.ui.screen.CounterScreen
import com.cwh.counterapp.ui.screen.HistoryScreen
import com.cwh.counterapp.ui.screen.HomeScreen
import com.cwh.counterapp.ui.screen.SettingsScreen

@Composable
fun CounterNavHost() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
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

            composable(Screen.Settings.route) {

                SettingsScreen()
            }
        }
    }
}