package com.example.weather.model.data

data class StepForecastData(
    val temperature: TemperatureData,
    val weather: WeatherData,
    val textDate: String
)