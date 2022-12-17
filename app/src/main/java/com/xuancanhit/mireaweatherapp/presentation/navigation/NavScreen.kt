package com.xuancanhit.mireaweatherapp.presentation.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(NavRoutes.homeScreen)
    object ManageCities : NavScreen(NavRoutes.manageCities)
}