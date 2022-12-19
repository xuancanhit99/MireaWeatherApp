package com.xuancanhit.mireaweatherapp.presentation.screens.manage

import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity

sealed interface ManageState {
    data class Success(val forecast: ForecastCity?): ManageState
    data class Error(val errorMessage: String?): ManageState

    object Loading: ManageState
}