package com.xuancanhit.mireaweatherapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xuancanhit.mireaweatherapp.presentation.screens.home.HomeScreen
import com.xuancanhit.mireaweatherapp.presentation.screens.home.HomeViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
    paddings: PaddingValues,
    homeViewModel: HomeViewModel,
    setFabOnClick: (() -> Unit) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route,
    ) {
        composable(route = NavScreen.HomeScreen.route) {
            HomeScreen(homeViewModel, paddings, setFabOnClick) { navController.navigate(NavScreen.ManageCities.route) }
        }
        composable(route = NavScreen.ManageCities.route) {
            //SettingsScreen( paddings)
        }
    }
}