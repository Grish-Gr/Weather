package com.example.weather.model.data

data class StepForecastData(
    val temperature: TemperatureData,
    val weather: StepWeatherData,
    val textDate: String
)