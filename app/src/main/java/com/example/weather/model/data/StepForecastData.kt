package com.example.weather.model.data

import com.example.weather.model.data.detail.StepWeatherDetail
import com.example.weather.model.data.detail.TemperatureDetail

data class StepForecastData(
    val temperature: TemperatureDetail,
    val weather: StepWeatherDetail,
    val textDate: String
)