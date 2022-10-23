package com.example.weather.model.interfaces

import com.example.weather.model.Result
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.StepForecastData

interface WeatherRepository {

    suspend fun getCurrentForecast(latitude: Float, longitude: Float): Result<CurrentForecastData>
    suspend fun getCurrentForecast(nameCity: String): Result<CurrentForecastData>

    suspend fun getStepForecast(latitude: Float, longitude: Float, count: Int = 5): Result<List<StepForecastData>>
    suspend fun getStepForecast(nameCity: String, count: Int): Result<List<StepForecastData>>
}