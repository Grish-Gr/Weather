package com.example.weather.domain.utils

import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SharedPreferencesRepository

class GetLastLocationUseCase(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    fun getLastLocation(): LocationData =
        sharedPreferencesRepository.getLastLocation()
}