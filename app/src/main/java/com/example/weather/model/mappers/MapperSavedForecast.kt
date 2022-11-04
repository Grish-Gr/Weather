package com.example.weather.model.mappers

import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.model.data.detail.WeatherDetail
import java.util.*
import javax.inject.Inject

class MapperSavedForecast @Inject constructor(
    private val mapperSavedLocation: Mapper<LocationEntity, LocationData>
): Mapper<Pair<LocationEntity, WeatherEntity>, SavedForecastData> {
    override fun mapping(source: Pair<LocationEntity, WeatherEntity>): SavedForecastData {
        val weather = source.second
        val temperature = TemperatureDetail(
            temperature = weather.temperature,
            feelsLikeTemperature = weather.feelsLikeTemperature
        )
        val weatherDetail = WeatherDetail(
            descriptionWeather = weather.description,
            pressure = weather.atmospherePressure,
            humidity = weather.cloudiness,
            cloudiness = weather.cloudiness,
            visibility = weather.visibility,
            windDirection = weather.directionWind
        )
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = weather.dateSave
        return SavedForecastData(
            temperature = temperature,
            weather = weatherDetail,
            date = calendar.time,
            location = mapperSavedLocation.mapping(source.first)
        )
    }
}