package com.example.weather.model.interfaces

import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.view.activities.LocationActivity

interface SavedWeatherRepository {

    suspend fun getWeathers(nameCity: String): List<SavedForecastData>
    suspend fun getWeathers(): List<SavedForecastData>
    suspend fun getLocation(latitude: Float, longitude: Float): LocationData?
    suspend fun getForecast(latitude: Float, longitude: Float): SavedForecastData

    suspend fun saveWeather(location: LocationData, forecast: CurrentForecastData)
    suspend fun deleteLocation(location: LocationData)
    suspend fun updateWeather(latitude: Float, longitude: Float, forecast: CurrentForecastData)
}