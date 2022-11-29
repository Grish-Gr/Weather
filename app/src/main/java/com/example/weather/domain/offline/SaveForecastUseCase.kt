package com.example.weather.domain.offline

import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SavedWeatherRepository
import javax.inject.Inject

class SaveForecastUseCase @Inject constructor(
    private val savedWeatherRepository: SavedWeatherRepository
) {

    suspend fun saveLocation(location: LocationData) =
        savedWeatherRepository.saveWeather(location, CurrentForecastData.defaultCurrentForecast)
}