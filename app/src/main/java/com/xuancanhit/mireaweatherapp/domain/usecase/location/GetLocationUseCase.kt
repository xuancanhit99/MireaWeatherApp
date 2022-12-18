package com.xuancanhit.mireaweatherapp.domain.usecase.location

import com.xuancanhit.mireaweatherapp.data.location.DefaultLocationTracker
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val defaultLocationTracker: DefaultLocationTracker) {
    suspend fun getLocation() = defaultLocationTracker.getCurrentLocation()
}