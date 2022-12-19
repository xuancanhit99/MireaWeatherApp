package com.xuancanhit.mireaweatherapp.presentation.screens.manage

import com.xuancanhit.mireaweatherapp.domain.model.MyCity


sealed interface MyCitiesState {
    data class Success(val forecast: List<MyCity>?): MyCitiesState
    data class Error(val errorMessage: String?): MyCitiesState

    object Loading: MyCitiesState
}