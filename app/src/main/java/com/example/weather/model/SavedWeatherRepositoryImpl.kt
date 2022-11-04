package com.example.weather.model

import android.util.Log
import com.example.weather.data.db.LocationDatabase
import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.interfaces.SavedWeatherRepository
import com.example.weather.model.mappers.Mapper
import com.example.weather.model.mappers.MapperWeatherEntity
import javax.inject.Inject

class SavedWeatherRepositoryImpl @Inject constructor(
    private val database: LocationDatabase,
    private val mapperForecast: Mapper<Pair<LocationEntity, WeatherEntity>, SavedForecastData>,
    private val mapperWeatherEntity: Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity>,
    private val mapperLocation: Mapper<LocationEntity, LocationData>,
    private val mapperLocationEntity: Mapper<LocationData, LocationEntity>
): SavedWeatherRepository {

    override suspend fun getWeathers(nameCity: String): List<SavedForecastData> {
        val locations = database.getDao().searchLocations(nameCity)
        val weathers = locations.map { location ->
            database.getDao().getWeather(location.latitude, location.longitude)
        }
        return locations.zip(weathers).map { entry ->
            mapperForecast.mapping(Pair(
                first = entry.first,
                second = entry.second ?: mapperWeatherEntity.mapping(Pair(LocationData.DefaultLocation, CurrentForecastData.defaultCurrentForecast))))
        }
    }

    override suspend fun getLocation(latitude: Float, longitude: Float): LocationData?{
        val location = database.getDao().getLocation(latitude, longitude)
        return if (location != null) mapperLocation.mapping(location) else null
    }

    override suspend fun getForecast(latitude: Float, longitude: Float): SavedForecastData =
        mapperForecast.mapping(Pair(
            first = database.getDao().getLocation(latitude, longitude)
                ?: mapperLocationEntity.mapping(LocationData.DefaultLocation),
            second = database.getDao().getWeather(latitude, longitude)
                ?: mapperWeatherEntity.mapping(Pair(
                    first = LocationData.DefaultLocation,
                    second = CurrentForecastData.defaultCurrentForecast)
                )
        ))

    override suspend fun saveWeather(location: LocationData, forecast: CurrentForecastData) {
        database.getDao().insertLocation(mapperLocationEntity.mapping(location))
        database.getDao().insertWeather(mapperWeatherEntity.mapping(Pair(location, forecast)))
    }

    override suspend fun deleteLocation(location: LocationData) {
        database.getDao().deleteSaveLocation(
            locationName = location.locationName,
            longitude = location.longitude,
            latitude = location.latitude)
    }

    override suspend fun updateWeather(latitude: Float, longitude: Float, forecast: CurrentForecastData) {
        val location = getLocation(latitude, longitude)
        Log.e("TAG", "Update ${forecast.toString()} in ${location?.locationName}")
        if (location != null)
            database.getDao().updateWeather(mapperWeatherEntity.mapping(Pair(location, forecast)))
    }
}