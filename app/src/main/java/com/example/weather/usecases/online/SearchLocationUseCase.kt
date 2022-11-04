package com.example.weather.usecases.online

import com.example.weather.model.ResultOf
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.GeocodingRepository
import javax.inject.Inject

class SearchLocationUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {

    suspend fun searchCityByLocation(
        latitude: Float,
        longitude: Float
    ): ResultOf<List<LocationData>> =
        geocodingRepository.getLocationByCoordination(latitude, longitude)

    suspend fun searchCityByName(nameCity: String): ResultOf<List<LocationData>> =
        geocodingRepository.getLocationByName(nameCity)
}