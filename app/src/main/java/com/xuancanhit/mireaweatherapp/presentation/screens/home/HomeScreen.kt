package com.xuancanhit.mireaweatherapp.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController



@Composable
fun HomeScreen(
    navHostController: NavHostController,
    padding: PaddingValues,
    setFabOnClick: (() -> Unit) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
//    LaunchedEffect(Unit) {
//        setFabOnClick { viewModel.fetchWeather() }
//    }

    val homeCurrentWeatherState by viewModel.homeForecastState.collectAsState()
    val activity = (LocalContext.current as? Activity)

//    Scaffold(
//        modifier = Modifier.fillMaxSize()) {
////        BackgroundImage()
////        MenuIcon { onNavigateToSearchCityScreen() }
////        WeatherSection(homeCurrentWeatherState) { activity?.finish() }
//    }

}