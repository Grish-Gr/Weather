package com.example.weather.usecases

import com.example.weather.model.Result
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.GeocodingRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {

    suspend fun searchCityByLocation(
        latitude: Float,
        longitude: Float
    ): Result<List<LocationData>> =
        geocodingRepository.getLocationByCoordination(latitude, longitude)

    suspend fun searchCityByName(nameCity: String): Result<List<LocationData>> =
        geocodingRepository.getLocationByName(nameCity)
}