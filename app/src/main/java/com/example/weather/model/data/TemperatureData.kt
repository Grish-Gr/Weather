package com.example.weather.model.data

import com.example.weather.R

data class TemperatureData(
    val temperature: Float,
    val feelsLikeTemperature: Float,
    val minTemperature: Float,
    val maxTemperature: Float
){

    companion object {
        fun getTemperatureCelsius(temperature: Float): String =
            String.format("%.1f°ᶜ", temperature)
    }
}