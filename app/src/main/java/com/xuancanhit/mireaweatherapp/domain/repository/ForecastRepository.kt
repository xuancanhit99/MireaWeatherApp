package com.xuancanhit.mireaweatherapp.domain.repository

import com.xuancanhit.mireaweatherapp.domain.model.City
import com.xuancanhit.mireaweatherapp.core.common.Resource
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity

interface ForecastRepository {
    suspend fun getForecastData(latitude: Double, longitude: Double, ): Resource<ForecastCity>

    suspend fun getForecastDataWithCityName(cityName: String): Resource<ForecastCity>

    suspend fun addForecastWeather(forecast: ForecastCity)

    suspend fun addCity(city: City)

    fun getForecastWeather() : ForecastCity?

    fun getCity() : City

    suspend fun updateForecastWeather(forecast: ForecastCity)

    suspend fun updateCity(city: City)
}