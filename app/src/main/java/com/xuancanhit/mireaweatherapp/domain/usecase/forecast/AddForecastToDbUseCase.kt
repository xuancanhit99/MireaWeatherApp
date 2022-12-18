package com.xuancanhit.mireaweatherapp.domain.usecase.forecast

import com.xuancanhit.mireaweatherapp.data.repository.ForecastRepositoryImpl
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity
import com.xuancanhit.mireaweatherapp.domain.model.ForecastWeather
import javax.inject.Inject

class AddForecastToDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun addForecastToDbUseCase(forecast: ForecastCity, forecastSize: Int) {
        for (i in 1..forecastSize) {
            forecastRepositoryImpl.addForecastWeather(
                ForecastCity(
                    listOf(
                        ForecastWeather(
                            i,
                            forecast.weatherList[i - 1].weatherData,
                            forecast.weatherList[i - 1].weatherStatus,
                            forecast.weatherList[i - 1].wind,
                            forecast.weatherList[i - 1].date,
                            forecast.weatherList[i - 1].cloudiness
                        )
                    ),
                    forecast.cityDtoData
                )
            )
        }
    }
}