package com.example.weather.model.mappers

import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.SunTimeData
import com.example.weather.model.data.TemperatureData
import com.example.weather.model.data.WeatherData

class MapperCurrentForecast: Mapper<CurrentForecastResponse, CurrentForecastData> {
    override fun mapping(source: CurrentForecastResponse): CurrentForecastData {
        val temperatureData = TemperatureData(
            temperature = source.infoTemperature.temperature,
            feelsLikeTemperature = source.infoTemperature.feelsLike,
            minTemperature = source.infoTemperature.minTemperature,
            maxTemperature = source.infoTemperature.maxTemperature
        )
        val weatherData = WeatherData(
            pressure = source.infoTemperature.pressure,
            humidity = source.infoTemperature.humidity,
            descriptionWeather = source.listWeather.first().description,
            urlIconWeather = source.listWeather.first().idIconWeather,
            cloudiness = source.clouds.cloudiness
        )
        val sunTimeData = SunTimeData(
            timeToSunrise = source.sys.timeToSunrise,
            timeToSunset = source.sys.timeToSunset
        )
        return CurrentForecastData(
            temperature = temperatureData,
            weather = weatherData,
            sunTime = sunTimeData,
            currentDate = source.timeOfData
        )
    }
}