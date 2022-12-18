package com.xuancanhit.mireaweatherapp.data.datasource.local.db

import com.xuancanhit.mireaweatherapp.data.datasource.local.db.entity.CityEntity
import com.xuancanhit.mireaweatherapp.data.datasource.local.db.entity.ForecastEntity
import com.xuancanhit.mireaweatherapp.data.datasource.local.db.room.CityDao
import com.xuancanhit.mireaweatherapp.data.datasource.local.db.room.ForecastDao
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao
) {

    suspend fun addForecastWeather(forecastEntity: ForecastEntity) =
        forecastDao.addForecastWeather(forecastEntity)

    suspend fun addCity(cityEntity: CityEntity) =
        cityDao.addCity(cityEntity)

    fun getForecastWeather() = forecastDao.getForecastWeather()
    fun getCity() = cityDao.getCity()

    suspend fun updateForecastWeather(forecastEntity: ForecastEntity) =
        forecastDao.updateForecastWeather(forecastEntity)

    suspend fun updateCity(cityEntity: CityEntity) =
        cityDao.updateCity(cityEntity)
}