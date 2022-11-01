package com.example.weather.model.data

import com.example.weather.model.data.detail.StepWeatherDetail
import com.example.weather.model.data.detail.TemperatureDetail
import java.util.*

data class StepForecastData(
    val temperature: TemperatureDetail,
    val weather: StepWeatherDetail,
    val date: Date
)