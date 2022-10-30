package com.example.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity

@Database(entities = [LocationEntity::class, WeatherEntity::class], version = 1)
abstract class LocationDatabase: RoomDatabase() {
    abstract fun getDao(): LocationDao
}