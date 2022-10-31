package com.example.weather.usecases

import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SaveLocationRepository
import javax.inject.Inject

class DeleteSavedLocationUseCase @Inject constructor(
    private val saveLocationRepository: SaveLocationRepository
) {

    suspend fun deleteLocation(location: LocationData) =
        saveLocationRepository.deleteLocation(location)
}