package com.xuancanhit.mireaweatherapp.data.mapper

import com.xuancanhit.mireaweatherapp.data.datasource.remote.api.entity.CityDto
import com.xuancanhit.mireaweatherapp.data.datasource.remote.api.entity.CoordDto
import com.xuancanhit.mireaweatherapp.domain.model.City
import com.xuancanhit.mireaweatherapp.domain.model.Coordinates
import javax.inject.Inject

class CityDtoMapper @Inject constructor() : IEntityMapper<CityDto, City> {
    override fun mapFromEntity(entity: CityDto): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            Coordinates(
                entity.coordinate.latitude,
                entity.coordinate.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityDto {
        return CityDto(
            model.country,
            model.timezone,
            model.sunrise,
            model.sunset,
            model.cityName,
            CoordDto(
                model.coordinate.latitude,
                model.coordinate.longitude
            )
        )
    }

}