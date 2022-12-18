package com.xuancanhit.mireaweatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xuancanhit.mireaweatherapp.presentation.navigation.NavGraph
import com.xuancanhit.mireaweatherapp.presentation.navigation.NavScreen
import com.xuancanhit.mireaweatherapp.presentation.screens.home.HomeViewModel
import com.xuancanhit.mireaweatherapp.presentation.screens.home.view.HomeTopBar
import com.xuancanhit.mireaweatherapp.ui.theme.MireaWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    //private val searchCityViewModel: SearchCityViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            homeViewModel.loadLocation()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )


        setContent {
            MireaWeatherAppTheme {
                val navController =  rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val (fabOnClick, setFabOnClick) = remember { mutableStateOf<(() -> Unit)?>(null)}
                val currentRoute = backStackEntry?.destination?.route
                Scaffold (
                    topBar = {
                        HomeTopBar(
                            when (currentRoute) {
                                NavScreen.HomeScreen.route -> "Weather"
                                NavScreen.ManageCities.route -> "Manage"
                                else -> ""
                            }
                        )
                    },

                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            icon = {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = null
                                )
                            },
                            text = { Text("Update") }, onClick = { fabOnClick?.invoke() }
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(NavScreen.HomeScreen.icon, contentDescription = null) },
                                label = { Text(NavScreen.HomeScreen.title) },
                                selected = NavScreen.HomeScreen.route == currentRoute,
                                onClick = {
                                    navController.navigate(NavScreen.HomeScreen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }

                                }
                            )
                            NavigationBarItem(
                                icon = { Icon(NavScreen.ManageCities.icon, contentDescription = null) },
                                label = { Text(NavScreen.ManageCities.title) },
                                selected = NavScreen.ManageCities.route == currentRoute,
                                onClick = {
                                    navController.navigate(NavScreen.ManageCities.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }

                                }
                            )
                        }
                    },

                    content = {
                        NavGraph(navController = navController, it, homeViewModel, setFabOnClick)
                    })
            }
        }
    }
}
