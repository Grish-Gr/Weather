package com.example.weather.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "locations",
    primaryKeys = ["latitude_location", "longitude_location"]
)
data class LocationEntity(

    @ColumnInfo(name = "latitude_location")
    val latitude: Float,

    @ColumnInfo(name = "longitude_location")
    val longitude: Float,

    @ColumnInfo(name = "name_location")
    val nameLocation: String,

    @ColumnInfo(name = "country_location")
    val countryLocation: String
)