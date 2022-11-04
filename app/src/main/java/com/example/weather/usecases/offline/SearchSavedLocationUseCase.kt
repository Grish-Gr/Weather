package com.example.weather.usecases.offline

import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.interfaces.SavedWeatherRepository
import javax.inject.Inject

class SearchSavedLocationUseCase @Inject constructor(
    private val savedWeatherRepository: SavedWeatherRepository
) {

    suspend fun searchLocation(cityName: String): List<SavedForecastData> =
        savedWeatherRepository.getWeathers(cityName)
}