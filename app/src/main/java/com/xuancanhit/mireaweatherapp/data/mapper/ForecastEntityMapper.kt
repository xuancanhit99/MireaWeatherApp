package com.xuancanhit.mireaweatherapp.data.mapper

import com.xuancanhit.mireaweatherapp.data.datasource.local.db.entity.CityEntity
import com.xuancanhit.mireaweatherapp.data.datasource.local.db.entity.ForecastEntity
import com.xuancanhit.mireaweatherapp.domain.model.*
import javax.inject.Inject

class ForecastEntityMapper @Inject constructor() {
    fun mapFromEntity(entityForecast: List<ForecastEntity>, entityCity: CityEntity): ForecastCity {
        return ForecastCity(
            entityForecast.map {
                ForecastWeather(
                    it.id,
                    Main(
                        it.temp,
                        it.feels_like,
                        it.pressure,
                        it.humidity
                    ),
                    listOf(
                        Weather(it.mainDescription, it.description)
                    ),
                    Wind(it.wind_speed),
                    it.date,
                    Cloudiness(it.cloudiness)
                )
            },
            City(
                entityCity.country,
                entityCity.timezone,
                entityCity.sunrise,
                entityCity.sunset,
                entityCity.cityName,
                Coordinates(
                    entityCity.longitude,
                    entityCity.latitude
                )
            )
        )
    }

    fun entityFromModel(model: ForecastCity): ForecastEntity {
        return ForecastEntity(
            id = model.weatherList[0].id,
            temp = model.weatherList[0].weatherData.temp,
            feels_like = model.weatherList[0].weatherData.feelsLike,
            pressure = model.weatherList[0].weatherData.pressure,
            humidity = model.weatherList[0].weatherData.humidity,
            wind_speed = model.weatherList[0].wind.speed,
            description = model.weatherList[0].weatherStatus[0].description,
            mainDescription = model.weatherList[0].weatherStatus[0].mainDescription,
            date = model.weatherList[0].date,
            cloudiness = model.weatherList[0].cloudiness.cloudiness
        )
    }
}