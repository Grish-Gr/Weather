package com.example.weather.model.data

import com.example.weather.model.data.detail.IconsDetail
import com.example.weather.model.data.detail.SunTimeDetail
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.model.data.detail.WeatherDetail
import java.util.*

sealed class ForecastData {
    abstract val temperature: TemperatureDetail
    abstract val weather: WeatherDetail
    abstract val date: Date
}

data class CurrentForecastData(
    override val temperature: TemperatureDetail,
    override val weather: WeatherDetail,
    override val date: Date,
    val icons: IconsDetail,
    val sunTime: SunTimeDetail
): ForecastData(){

    companion object{
        val defaultCurrentForecast = CurrentForecastData(
            temperature = TemperatureDetail(
                temperature = 0.0f,
                feelsLikeTemperature = 0.0f
            ),
            weather = WeatherDetail(
                descriptionWeather = "",
                pressure = 0,
                humidity = 0,
                cloudiness = 0,
                visibility = 0,
                speedWinder = 0
            ),
            date = Date(),
            icons = IconsDetail(urlIconWeatherHeight = "", urlIconWeather = ""),
            sunTime = SunTimeDetail(dateSunrise = Date(), dateSunset = Date())
        )
    }
}

data class SavedForecastData(
    override val temperature: TemperatureDetail,
    override val weather: WeatherDetail,
    override val date: Date,
    val location: LocationData
): ForecastData()