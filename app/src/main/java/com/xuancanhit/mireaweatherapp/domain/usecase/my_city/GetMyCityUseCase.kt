package com.xuancanhit.mireaweatherapp.domain.usecase.my_city

import com.xuancanhit.mireaweatherapp.data.repository.MyCityRepositoryImpl
import javax.inject.Inject

class GetMyCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    fun getMyCity() = myCityRepositoryImpl.getMyCity()
}