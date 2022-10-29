package com.example.weather.model.interfaces

import com.example.weather.model.data.LocationData
import com.example.weather.model.ResultOf

interface GeocodingRepository {

    suspend fun getLocationByName(nameCity: String, count: Int = 1): ResultOf<List<LocationData>>
    suspend fun getLocationByCoordination(latitude: Float, longitude: Float, count: Int = 1): ResultOf<List<LocationData>>
}