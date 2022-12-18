package com.xuancanhit.mireaweatherapp.domain.usecase.forecast

import com.xuancanhit.mireaweatherapp.data.repository.ForecastRepositoryImpl
import com.xuancanhit.mireaweatherapp.domain.model.City
import javax.inject.Inject

class UpdateCityDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun updateCityDb(city: City) = forecastRepositoryImpl.updateCity(city)
}