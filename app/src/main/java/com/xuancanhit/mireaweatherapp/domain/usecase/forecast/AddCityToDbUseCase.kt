package com.xuancanhit.mireaweatherapp.domain.usecase.forecast

import com.xuancanhit.mireaweatherapp.data.repository.ForecastRepositoryImpl
import com.xuancanhit.mireaweatherapp.domain.model.City
import javax.inject.Inject

class AddCityToDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun addCityDb(city: City) = forecastRepositoryImpl.addCity(city)
}