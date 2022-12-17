package com.xuancanhit.mireaweatherapp.domain.model

data class ForecastCity(
    val weatherList: List<ForecastWeather>,
    val cityDtoData: City
)
