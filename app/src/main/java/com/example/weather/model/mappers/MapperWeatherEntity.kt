package com.example.weather.model.mappers

import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import java.util.*

class MapperWeatherEntity: Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity> {
    override fun mapping(source: Pair<LocationData, CurrentForecastData>): WeatherEntity {
        val location = source.first
        val forecast = source.second
        return WeatherEntity(
            temperature = forecast.temperature.temperature,
            dateSave = getDateUTC(forecast.date),
            description = forecast.weather.descriptionWeather,
            feelsLikeTemperature = forecast.temperature.feelsLikeTemperature,
            speedWinder = forecast.weather.speedWinder,
            atmospherePressure = forecast.weather.pressure,
            visibility = forecast.weather.visibility,
            humidity = forecast.weather.humidity,
            cloudiness = forecast.weather.cloudiness,
            latitudeLocation = location.latitude,
            locationLongitude = location.longitude
        )
    }

    private fun getDateUTC(date: Date): Long{
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.timeInMillis
    }
}