package com.example.weather.model.mappers

import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData

class MapperWeatherEntity: Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity> {
    override fun mapping(source: Pair<LocationData, CurrentForecastData>): WeatherEntity {
        val location = source.first
        val forecast = source.second
        return WeatherEntity(
            id = 0,
            temperature = forecast.temperature.temperature,
            dateSave = forecast.date.time,
            description = forecast.weather.descriptionWeather,
            feelsLikeTemperature = forecast.temperature.feelsLikeTemperature,
            directionWind = forecast.weather.windDirection,
            atmospherePressure = forecast.weather.pressure,
            visibility = forecast.weather.visibility,
            humidity = forecast.weather.humidity,
            cloudiness = forecast.weather.cloudiness,
            latitudeLocation = location.latitude,
            locationLongitude = location.longitude
        )
    }
}