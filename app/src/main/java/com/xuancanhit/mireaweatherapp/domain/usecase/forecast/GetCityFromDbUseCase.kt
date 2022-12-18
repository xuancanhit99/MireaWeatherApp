package com.xuancanhit.mireaweatherapp.domain.usecase.forecast

import com.xuancanhit.mireaweatherapp.data.repository.ForecastRepositoryImpl
import javax.inject.Inject

class GetCityFromDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    fun getCityFromDb() = forecastRepositoryImpl.getCity()
}