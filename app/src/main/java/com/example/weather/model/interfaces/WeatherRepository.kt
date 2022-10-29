package com.example.weather.model.interfaces

import com.example.weather.model.ResultOf
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.StepForecastData

interface WeatherRepository {

    suspend fun getCurrentForecast(latitude: Float, longitude: Float): ResultOf<CurrentForecastData>
    suspend fun getCurrentForecast(nameCity: String): ResultOf<CurrentForecastData>

    suspend fun getStepForecast(latitude: Float, longitude: Float, count: Int = 5): ResultOf<List<StepForecastData>>
    suspend fun getStepForecast(nameCity: String, count: Int): ResultOf<List<StepForecastData>>
}