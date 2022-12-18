package com.xuancanhit.mireaweatherapp.data.repository

import com.xuancanhit.mireaweatherapp.data.datasource.local.db.CityLocalDataSource
import com.xuancanhit.mireaweatherapp.data.datasource.local.db.ForecastLocalDataSource
import com.xuancanhit.mireaweatherapp.data.datasource.remote.api.ForecastRemoteDataSource
import com.xuancanhit.mireaweatherapp.data.mapper.CityEntityMapper
import com.xuancanhit.mireaweatherapp.data.mapper.ForecastDtoMapper
import com.xuancanhit.mireaweatherapp.data.mapper.ForecastEntityMapper
import com.xuancanhit.mireaweatherapp.domain.model.City
import com.xuancanhit.mireaweatherapp.domain.repository.ForecastRepository
import com.xuancanhit.mireaweatherapp.core.common.Resource
import com.xuancanhit.mireaweatherapp.core.utils.Constants
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource,
    private val cityLocalDataSource: CityLocalDataSource,
    private val dtoMapper: ForecastDtoMapper,
    private val entityMapper: ForecastEntityMapper,
    private val cityEntityMapper: CityEntityMapper
) : ForecastRepository {
    override suspend fun getForecastData(
        latitude: Double,
        longitude: Double
    ): Resource<ForecastCity> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastData(
                        latitude,
                        longitude
                    )
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun getForecastDataWithCityName(cityName: String): Resource<ForecastCity> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastDataWithCityName(cityName)
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun addForecastWeather(forecast: ForecastCity) {
        forecastLocalDataSource.addForecastWeather(
            entityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun addCity(city: City) {
        forecastLocalDataSource.addCity(
            cityEntityMapper.entityFromModel(city)
        )
    }

    override fun getForecastWeather(): ForecastCity? {
        return if (forecastLocalDataSource.getForecastWeather().isNullOrEmpty()) {
            null
        } else {
            entityMapper.mapFromEntity(
                forecastLocalDataSource.getForecastWeather(),
                cityLocalDataSource.getCity()
            )
        }
    }

    override fun getCity(): City {
        return cityEntityMapper.mapFromEntity(forecastLocalDataSource.getCity())
    }

    override suspend fun updateForecastWeather(forecast: ForecastCity) {
        forecastLocalDataSource.updateForecastWeather(
            entityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun updateCity(city: City) {
        forecastLocalDataSource.updateCity(
            cityEntityMapper.entityFromModel(city)
        )
    }
}