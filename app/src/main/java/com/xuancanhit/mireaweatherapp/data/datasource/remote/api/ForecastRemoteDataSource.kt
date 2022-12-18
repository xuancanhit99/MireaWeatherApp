package com.xuancanhit.mireaweatherapp.data.datasource.remote.api

import com.xuancanhit.mireaweatherapp.data.datasource.remote.api.weatherapi.WeatherApi
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherApi) {
    suspend fun getForecastData(latitude: Double, longitude: Double) =
        api.getForecastData(latitude, longitude)

    suspend fun getForecastDataWithCityName(cityName: String) =
        api.getForecastDataWithCityName(cityName)
}