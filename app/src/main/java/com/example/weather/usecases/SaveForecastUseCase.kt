package com.example.weather.usecases

import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SaveLocationRepository
import javax.inject.Inject

class SaveForecastUseCase @Inject constructor(
    private val saveLocationRepository: SaveLocationRepository
) {

    suspend fun saveLocation(location: LocationData, currentForecast: CurrentForecastData) =
        saveLocationRepository.saveWeather(location, currentForecast)
}