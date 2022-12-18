package com.xuancanhit.mireaweatherapp.data.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}