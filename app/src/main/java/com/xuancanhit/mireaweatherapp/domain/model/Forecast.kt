package com.xuancanhit.mireaweatherapp.domain.model

data class Forecast(
    val weatherList: List<ForecastWeather>,
    val cityDtoData: City
)
