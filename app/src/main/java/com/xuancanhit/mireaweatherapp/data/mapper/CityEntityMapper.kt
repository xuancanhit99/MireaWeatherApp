package com.xuancanhit.mireaweatherapp.data.mapper

import com.xuancanhit.mireaweatherapp.data.datasource.local.db.entity.CityEntity
import com.xuancanhit.mireaweatherapp.domain.model.City
import com.xuancanhit.mireaweatherapp.domain.model.Coordinates
import javax.inject.Inject

class CityEntityMapper @Inject constructor() : IEntityMapper<CityEntity, City> {
    override fun mapFromEntity(entity: CityEntity): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            Coordinates(
                entity.latitude,
                entity.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityEntity {
        return CityEntity(
            id = 1,
            country = model.country,
            timezone = model.timezone,
            sunrise = model.sunrise,
            sunset = model.sunset,
            cityName = model.cityName,
            latitude = model.coordinate.latitude,
            longitude = model.coordinate.longitude
        )
    }
}