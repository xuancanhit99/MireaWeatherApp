package com.xuancanhit.mireaweatherapp.presentation.home

import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity

sealed interface HomeForecastState {
    data class Success(val forecast: ForecastCity?): HomeForecastState
    data class Error(val errorMessage: String?): HomeForecastState
    object Loading: HomeForecastState
}