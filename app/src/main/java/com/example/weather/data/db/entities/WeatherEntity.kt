package com.example.weather.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "weathers",
    foreignKeys = [ForeignKey(
        entity = LocationEntity::class,
        parentColumns = ["latitude_location", "longitude_location"],
        childColumns = ["location_latitude", "location_longitude"],
        onUpdate = CASCADE,
        onDelete = CASCADE)]
)
data class WeatherEntity(

    @ColumnInfo(name = "id_weather")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "temperature_weather")
    val temperature: Float,

    @ColumnInfo(name = "date_save_weather")
    val dateSave: Long,

    @ColumnInfo(name = "description_weather")
    val description: String,

    @ColumnInfo(name = "feels_like_temp")
    val feelsLikeTemperature: Float,

    @ColumnInfo(name = "direction_wind")
    val directionWind: String,

    @ColumnInfo(name = "atm_pressure")
    val atmospherePressure: Int,

    @ColumnInfo(name = "visibility")
    val visibility: Int,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "cloudiness")
    val cloudiness: Int,

    @ColumnInfo(name = "location_latitude")
    val latitudeLocation: Float,

    @ColumnInfo(name = "location_longitude")
    val locationLongitude: Float
)