package com.example.weather.usecases

import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.interfaces.SaveLocationRepository
import javax.inject.Inject

class SearchSavedLocationUseCase @Inject constructor(
    private val saveLocationRepository: SaveLocationRepository
) {

    suspend fun searchLocation(cityName: String): List<SavedForecastData> =
        saveLocationRepository.getWeathers(cityName)
}