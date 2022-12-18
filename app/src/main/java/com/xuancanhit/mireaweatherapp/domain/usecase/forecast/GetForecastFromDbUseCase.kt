package com.xuancanhit.mireaweatherapp.domain.usecase.forecast

import com.xuancanhit.mireaweatherapp.data.repository.ForecastRepositoryImpl
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity
import javax.inject.Inject

class GetForecastFromDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    fun getForecastFromDbUseCase() : ForecastCity? = forecastRepositoryImpl.getForecastWeather()
}