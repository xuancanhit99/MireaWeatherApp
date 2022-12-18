package com.xuancanhit.mireaweatherapp.domain.usecase.my_city

import com.xuancanhit.mireaweatherapp.data.repository.MyCityRepositoryImpl
import javax.inject.Inject

class GetSpecificCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    suspend fun getSpecificCityUseCase(cityName: String) =
        myCityRepositoryImpl.getSpecificCity(cityName)
}