package com.example.weather.model.mappers

import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.TemperatureData
import com.example.weather.model.data.WeatherData

class MapperStepForecast: Mapper<StepForecast, StepForecastData> {
    override fun mapping(source: StepForecast): StepForecastData {
        with(source){
            val temperatureData = TemperatureData(
                temperature = infoTemperatures.temperature,
                feelsLikeTemperature = infoTemperatures.feelsLike,
                minTemperature = infoTemperatures.minTemperature,
                maxTemperature = infoTemperatures.maxTemperature
            )
            val weatherData = WeatherData(
                pressure = infoTemperatures.pressure,
                humidity = infoTemperatures.humidity,
                descriptionWeather = weather.first().description,
                urlIconWeather = weather.first().idIconWeather,
                cloudiness = clouds.cloudiness
            )
            return StepForecastData(
                temperature = temperatureData,
                weather = weatherData,
                textDate = textDate
            )
        }
    }

}