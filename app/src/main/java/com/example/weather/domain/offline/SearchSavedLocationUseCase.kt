package com.example.weather.domain.offline

import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.interfaces.SavedWeatherRepository
import javax.inject.Inject

class SearchSavedLocationUseCase @Inject constructor(
    private val savedWeatherRepository: SavedWeatherRepository
) {

    suspend fun searchLocation(cityName: String): List<SavedForecastData> =
        savedWeatherRepository.getWeathers(cityName)

    suspend fun getAllLocation(): List<SavedForecastData> =
        savedWeatherRepository.getWeathers()
}