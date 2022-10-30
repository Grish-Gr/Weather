package com.example.weather.model.mappers

import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.model.data.LocationData

class MapperSavedLocation: Mapper<LocationEntity, LocationData> {
    override fun mapping(source: LocationEntity): LocationData {
        return LocationData(
            locationName = source.nameLocation,
            latitude = source.latitude,
            longitude = source.longitude,
            country = source.countryLocation
        )
    }
}