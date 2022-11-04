package com.example.weather.usecases.offline

import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.interfaces.SavedWeatherRepository

class GetSavedForecastUseCase(
    private val repository: SavedWeatherRepository
) {

    suspend fun getSavedForecastData(latitude: Float, longitude: Float): SavedForecastData =
        repository.getForecast(latitude, longitude)
}