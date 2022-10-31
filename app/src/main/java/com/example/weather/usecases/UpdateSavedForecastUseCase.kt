package com.example.weather.usecases

import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SaveLocationRepository
import javax.inject.Inject

class UpdateSavedForecastUseCase @Inject constructor(
    private val saveLocationRepository: SaveLocationRepository
) {

    suspend fun updateSavedForecast(location: LocationData, currentForecast: CurrentForecastData) =
        saveLocationRepository.updateWeather(location, currentForecast)
}