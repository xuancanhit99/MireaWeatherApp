package com.xuancanhit.mireaweatherapp.presentation.home

sealed interface HomeForecastState {
    data class Success(val forecast: Forecast?): HomeForecastState
    data class Error(val errorMessage: String?): HomeForecastState
    object Loading: HomeForecastState
}