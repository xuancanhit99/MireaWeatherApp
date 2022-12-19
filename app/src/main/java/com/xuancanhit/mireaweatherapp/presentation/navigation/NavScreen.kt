package com.xuancanhit.mireaweatherapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen(val route: String, val title: String, val icon: ImageVector) {
    object HomeScreen : NavScreen("home_screen", "Home", Icons.Filled.Home)
    object ManageCities : NavScreen("manage_cities_screen", "Manage", Icons.Filled.Settings)
}