package com.example.weather.model.data

data class WeatherData(
    val pressure: Int,
    val humidity: Int,
    val descriptionWeather: String,
    val urlIconWeather: String,
    val urlIconWeatherHeight: String,
    val cloudiness: Int,
    val visibility: Int,
    val windDirection: Int
)