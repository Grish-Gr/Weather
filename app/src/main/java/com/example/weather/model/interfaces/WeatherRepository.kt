package com.example.weather.model.interfaces

import com.example.weather.model.ResultOf
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.StepForecastData

interface WeatherRepository {

    suspend fun getCurrentForecast(latitude: Float, longitude: Float): ResultOf<ForecastData>
    suspend fun getStepForecast(latitude: Float, longitude: Float, count: Int = 5): ResultOf<List<StepForecastData>>
    suspend fun getStepForecast(latitude: Float, longitude: Float): ResultOf<List<StepForecastData>>
}