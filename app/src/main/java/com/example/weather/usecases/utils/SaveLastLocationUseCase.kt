package com.example.weather.usecases.utils

import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SharedPreferencesRepository

class SaveLastLocationUseCase(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    fun saveLocation(location: LocationData) =
        sharedPreferencesRepository.saveLastLocation(location)
}