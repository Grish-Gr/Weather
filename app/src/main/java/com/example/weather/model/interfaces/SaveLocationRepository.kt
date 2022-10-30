package com.example.weather.model.interfaces

import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData

interface SaveLocationRepository {

    suspend fun getWeathers(nameCity: String): List<SavedForecastData>

    suspend fun saveWeather(location: LocationData, forecast: CurrentForecastData)

    suspend fun deleteLocation(location: LocationData)

    suspend fun updateWeather(location: LocationData, forecast: CurrentForecastData)
}