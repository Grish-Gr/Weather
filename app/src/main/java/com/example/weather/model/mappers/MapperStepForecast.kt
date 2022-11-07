package com.example.weather.model.mappers

import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.detail.StepWeatherDetail
import com.example.weather.model.data.detail.TemperatureDetail
import java.text.SimpleDateFormat
import java.util.*

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
                descriptionWeather = weather.first().description.replaceFirstChar { it.uppercase() },
                urlIconWeather = getUrlByIcon(weather.first().idIconWeather),
                urlIconWeatherHeight = getUrlByIconHeight(weather.first().idIconWeather)

            )
            return StepForecastData(
                temperature = temperatureData,
                weather = weatherData,
                date = getDateByString(textDate)
            )
        }
    }

    private fun getDateByString(dateString: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.parse(dateString)
    }

    private fun getUrlByIcon(idIcon: String): String =
        "http://openweathermap.org/img/wn/${idIcon}@2x.png"

    private fun getUrlByIconHeight(idIcon: String): String =
        "http://openweathermap.org/img/wn/${idIcon}@4x.png"
}