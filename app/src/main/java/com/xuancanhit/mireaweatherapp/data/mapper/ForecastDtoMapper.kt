package com.xuancanhit.mireaweatherapp.data.mapper

import com.xuancanhit.mireaweatherapp.data.datasource.remote.api.entity.*
import com.xuancanhit.mireaweatherapp.domain.model.*
import javax.inject.Inject

class ForecastDtoMapper @Inject constructor() : IEntityMapper<ForecastDto, ForecastCity> {
    override fun mapFromEntity(entity: ForecastDto): ForecastCity {
        val forecastWeather: List<ForecastWeather> = entity.weatherList.map {
            ForecastWeather(
                weatherData = Main(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                weatherStatus = listOf(
                    Weather(it.weatherStatus[0].mainDescription, it.weatherStatus[0].description)
                ),
                wind = Wind(it.wind.speed),
                date = it.date,
                cloudiness = Cloudiness(it.cloudinessDto.cloudiness)
            )
        }

        return ForecastCity(
            forecastWeather,
            City(
                entity.cityDtoData.country,
                entity.cityDtoData.timezone,
                entity.cityDtoData.sunrise,
                entity.cityDtoData.sunset,
                entity.cityDtoData.cityName,
                Coordinates(
                    entity.cityDtoData.coordinate.latitude,
                    entity.cityDtoData.coordinate.longitude
                )
            )
        )
    }

    override fun entityFromModel(model: ForecastCity): ForecastDto {
        val forecastWeatherDto: List<ForecastWeatherDto> = model.weatherList.map {
            ForecastWeatherDto(
                MainDto(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                listOf(
                    WeatherDto(it.weatherStatus[0].mainDescription, it.weatherStatus[0].description)
                ),
                WindDto(it.wind.speed),
                it.date,
                CloudinessDto(it.cloudiness.cloudiness)
            )
        }

        return ForecastDto(
            forecastWeatherDto,
            CityDto(
                model.cityDtoData.country,
                model.cityDtoData.timezone,
                model.cityDtoData.sunrise,
                model.cityDtoData.sunset,
                model.cityDtoData.cityName,
                CoordDto(
                    model.cityDtoData.coordinate.latitude,
                    model.cityDtoData.coordinate.longitude
                )
            )
        )
    }
}