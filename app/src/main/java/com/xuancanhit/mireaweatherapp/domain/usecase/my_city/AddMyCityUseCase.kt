package com.xuancanhit.mireaweatherapp.domain.usecase.my_city

import com.xuancanhit.mireaweatherapp.data.repository.MyCityRepositoryImpl
import com.xuancanhit.mireaweatherapp.domain.model.MyCity
import javax.inject.Inject

class AddMyCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    suspend fun addMyCity(myCity: MyCity) = myCityRepositoryImpl.addMyCity(myCity)
}