package com.example.weather.usecases.offline

import com.example.weather.model.interfaces.SavedWeatherRepository
import javax.inject.Inject

class CheckSavedLocationUseCase @Inject constructor(
    private val savedWeatherRepository: SavedWeatherRepository
) {

    suspend fun isSavedLocation(latitude: Float, longitude: Float): Boolean =
        savedWeatherRepository.getLocation(latitude, longitude) != null
}