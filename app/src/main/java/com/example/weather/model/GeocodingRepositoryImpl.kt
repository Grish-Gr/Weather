package com.example.weather.model

import com.example.weather.data.network.GeocodingService
import com.example.weather.data.network.model.response.LocationResponse
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.mappers.Mapper
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val geocodingService: GeocodingService,
    private val mapperLocation: Mapper<LocationResponse, LocationData>
): GeocodingRepository{
    override suspend fun getLocationByName(
        nameCity: String,
        count: Int
    ): ResultOf<List<LocationData>> {
        return try{
             ResultOf.Success(geocodingService.getLocationCity(nameCity).map {
                mapperLocation.mapping(it)
            })
        } catch (ex: Exception){
            ResultOf.Error(ex.toString())
        }
    }

    override suspend fun getLocationByCoordination(
        latitude: Float,
        longitude: Float,
        count: Int
    ): ResultOf<List<LocationData>> {
        return try{
            ResultOf.Success(geocodingService.getCityByLocation(latitude, longitude, count).map {
                mapperLocation.mapping(it)
            })
        } catch (ex: Exception){
            ResultOf.Error(ex.toString())
        }
    }
}