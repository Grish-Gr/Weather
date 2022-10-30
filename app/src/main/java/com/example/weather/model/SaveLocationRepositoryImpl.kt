package com.example.weather.model

import com.example.weather.data.db.LocationDatabase
import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.model.data.detail.WeatherDetail
import com.example.weather.model.interfaces.SaveLocationRepository
import com.example.weather.model.mappers.Mapper
import java.util.*
import javax.inject.Inject

class SaveLocationRepositoryImpl @Inject constructor(
    private val database: LocationDatabase,
    private val mapperForecast: Mapper<Pair<LocationEntity, WeatherEntity>, SavedForecastData>,
    private val mapperEntity: Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity>
): SaveLocationRepository {

    override suspend fun getWeathers(nameCity: String): List<SavedForecastData> {
        val locations = database.getDao().searchLocations(nameCity)
        val weathers = locations.map { location ->
            database.getDao().getWeather(location.latitude, location.longitude)
        }
        return locations.zip(weathers).map { entry ->
            mapperForecast.mapping(Pair(entry.first, entry.second))
        }
    }

    override suspend fun saveWeather(location: LocationData, forecast: CurrentForecastData) {
        database.getDao().insertLocation(LocationEntity(
            latitude = location.latitude,
            longitude = location.longitude,
            nameLocation = location.locationName,
            countryLocation = location.country)
        )
        database.getDao().insertWeather(mapperEntity.mapping(Pair(location, forecast)))
    }

    override suspend fun deleteLocation(location: LocationData) {
        database.getDao().deleteSaveLocation(
            locationName = location.locationName,
            longitude = location.longitude,
            latitude = location.latitude)
    }

    override suspend fun updateWeather(location: LocationData, forecast: CurrentForecastData) {
        database.getDao().updateWeather(mapperEntity.mapping(Pair(location, forecast)))
    }
}