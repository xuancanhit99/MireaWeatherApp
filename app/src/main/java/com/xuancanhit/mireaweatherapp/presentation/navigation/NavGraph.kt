package com.xuancanhit.mireaweatherapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph(
    navController: NavHostController,
    paddings: PaddingValues,
    setFabOnClick: (() -> Unit) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route,
    ) {
        composable(route = NavScreen.HomeScreen.route) {
            //HomeScreen(navController = navController, paddings, setFabOnClick)
        }
        composable(route = NavScreen.ManageCities.route) {
            //SettingsScreen( paddings)
        }
    }
}