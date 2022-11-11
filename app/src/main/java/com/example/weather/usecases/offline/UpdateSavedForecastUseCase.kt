package com.example.weather.usecases.offline

import android.util.Log
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.interfaces.SavedWeatherRepository
import javax.inject.Inject

class UpdateSavedForecastUseCase @Inject constructor(
    private val savedWeatherRepository: SavedWeatherRepository
) {

    suspend fun updateSavedForecast(latitude: Float, longitude: Float, currentForecast: CurrentForecastData){
        Log.e("TAG", "Update Saved Forecast")
        savedWeatherRepository.updateWeather(latitude, longitude, currentForecast)
    }
}