package com.example.weather.model.data

data class CurrentForecastData(
    val temperature: TemperatureData,
    val weather: WeatherData,
    val sunTime: SunTimeData,
    val currentDate: Long
)