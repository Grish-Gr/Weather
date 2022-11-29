package com.example.weather.domain.offline

import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SavedWeatherRepository
import javax.inject.Inject

class DeleteSavedLocationUseCase @Inject constructor(
    private val savedWeatherRepository: SavedWeatherRepository
) {

    suspend fun deleteLocation(location: LocationData) =
        savedWeatherRepository.deleteLocation(location)
}