package com.example.bankcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bankcard.ui.screens.BinSearchScreen
import com.example.bankcard.ui.screens.HistoryScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BinSearch.route
    ) {
        composable(Screen.BinSearch.route) {
            BinSearchScreen(
                onNavigateToHistory = {
                    navController.navigate(Screen.History.route)
                }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object BinSearch : Screen("bin_search")
    object History : Screen("history")
}