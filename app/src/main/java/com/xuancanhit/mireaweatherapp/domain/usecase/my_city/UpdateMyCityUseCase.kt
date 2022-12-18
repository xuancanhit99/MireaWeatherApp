package com.xuancanhit.mireaweatherapp.domain.usecase.my_city

import com.xuancanhit.mireaweatherapp.data.repository.MyCityRepositoryImpl
import com.xuancanhit.mireaweatherapp.domain.model.MyCity
import javax.inject.Inject

class UpdateMyCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    suspend fun updateMyCityUseCase(myCity: MyCity) = myCityRepositoryImpl.updateMyCity(myCity)
}