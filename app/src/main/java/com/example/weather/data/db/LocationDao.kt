package com.example.weather.data.db

import androidx.room.*
import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations WHERE name_location LIKE '%' || :nameCity || '%'")
    suspend fun searchLocations(nameCity: String): List<LocationEntity>

    @Query("SELECT * FROM weathers WHERE location_latitude = :latitude AND location_longitude = :longitude")
    suspend fun getWeather(latitude: Float, longitude: Float): WeatherEntity?

    @Query("SELECT * FROM locations WHERE latitude_location = :latitude AND longitude_location = :longitude")
    suspend fun getLocation(latitude: Float, longitude: Float): LocationEntity?

    @Insert
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Insert
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Query("DELETE FROM locations " +
            "WHERE name_location = :locationName AND latitude_location = :latitude AND longitude_location = :longitude")
    suspend fun deleteSaveLocation(locationName: String, latitude: Float, longitude: Float)

    @Update
    suspend fun updateWeather(weatherEntity: WeatherEntity)
}