package com.example.weather.model.mappers

import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.model.data.LocationData

class MapperLocationEntity: Mapper<LocationData, LocationEntity> {
    override fun mapping(source: LocationData): LocationEntity = LocationEntity(
        latitude = source.latitude,
        longitude = source.longitude,
        nameLocation = source.locationName,
        countryLocation = source.country)
}