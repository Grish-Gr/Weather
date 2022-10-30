package com.example.weather.model.data.detail

data class TemperatureDetail(
    val temperature: Float,
    val feelsLikeTemperature: Float,
    val minTemperature: Float? = null,
    val maxTemperature: Float? = null
){

    companion object {
        fun getTemperatureCelsius(temperature: Float): String =
            String.format("%.1f°ᶜ", temperature)
    }
}