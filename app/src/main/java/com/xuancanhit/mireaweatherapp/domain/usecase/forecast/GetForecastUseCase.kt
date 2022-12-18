package com.xuancanhit.mireaweatherapp.domain.usecase.forecast

import com.xuancanhit.mireaweatherapp.data.repository.ForecastRepositoryImpl
import com.xuancanhit.mireaweatherapp.core.common.Resource
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun getForecast(latitude: Double, longitude: Double): Resource<ForecastCity> =
        forecastRepositoryImpl.getForecastData(latitude, longitude)
}