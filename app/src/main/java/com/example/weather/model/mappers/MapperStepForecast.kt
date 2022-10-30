package com.example.weather.model.mappers

import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.detail.StepWeatherDetail
import com.example.weather.model.data.detail.TemperatureDetail

class MapperStepForecast: Mapper<StepForecast, StepForecastData> {
    override fun mapping(source: StepForecast): StepForecastData {
        with(source){
            val temperatureData = TemperatureDetail(
                temperature = infoTemperatures.temperature,
                feelsLikeTemperature = infoTemperatures.feelsLike,
                minTemperature = infoTemperatures.minTemperature,
                maxTemperature = infoTemperatures.maxTemperature
            )
            val weatherData = StepWeatherDetail(
                descriptionWeather = weather.first().description,
                urlIconWeather = getUrlByIcon(weather.first().idIconWeather),
                urlIconWeatherHeight = getUrlByIconHeight(weather.first().idIconWeather)

            )
            return StepForecastData(
                temperature = temperatureData,
                weather = weatherData,
                textDate = textDate
            )
        }
    }

    private fun getUrlByIcon(idIcon: String): String =
        "http://openweathermap.org/img/wn/${idIcon}@2x.png"

    private fun getUrlByIconHeight(idIcon: String): String =
        "http://openweathermap.org/img/wn/${idIcon}@4x.png"
}