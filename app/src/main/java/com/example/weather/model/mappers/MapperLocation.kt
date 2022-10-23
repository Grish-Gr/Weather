package com.example.weather.model.mappers

import com.example.weather.data.network.model.response.LocationResponse
import com.example.weather.model.data.LocationData

class MapperLocation: Mapper<LocationResponse, LocationData> {
    override fun mapping(source: LocationResponse): LocationData {
        return LocationData(
            locationName = source.name,
            latitude = source.latitude,
            longitude = source.longitude,
            country = source.country
        )
    }

}