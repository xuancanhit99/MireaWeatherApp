package com.xuancanhit.mireaweatherapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xuancanhit.mireaweatherapp.presentation.screens.home.HomeScreen
import com.xuancanhit.mireaweatherapp.presentation.screens.home.HomeViewModel
import com.xuancanhit.mireaweatherapp.presentation.screens.manage.ManageScreen
import com.xuancanhit.mireaweatherapp.presentation.screens.manage.ManageViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
    paddings: PaddingValues,
    homeViewModel: HomeViewModel,
    manageViewModel: ManageViewModel,
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
            ManageScreen(manageViewModel) {
                navController.navigate(NavScreen.HomeScreen.route) {
                    launchSingleTop = true
                    popUpTo(NavScreen.HomeScreen.route)
                }
            }
            //SettingsScreen( paddings)
        }
    }
}