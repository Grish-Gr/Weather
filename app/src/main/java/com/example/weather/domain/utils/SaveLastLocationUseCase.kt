package com.example.weather.domain.utils

import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SharedPreferencesRepository
import javax.inject.Inject

class SaveLastLocationUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    fun saveLocation(location: LocationData) =
        sharedPreferencesRepository.saveLastLocation(location)
}