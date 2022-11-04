package com.example.weather.model.interfaces

import com.example.weather.model.data.LocationData

interface SharedPreferencesRepository {

    fun saveLastLocation(location: LocationData)

    fun getLastLocation(): LocationData
}