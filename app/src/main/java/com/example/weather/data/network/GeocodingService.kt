package com.example.weather.data.network

import com.example.weather.data.network.model.response.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {

    @GET("./geo/1.0/direct")
    suspend fun getLocationCity(
        @Query("q") nameCity: String,
        @Query("limit") count: Int = 5): List<LocationResponse>

    @GET("/geo/1.0/reverse")
    suspend fun getCityByLocation(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("limit") count: Int): List<LocationResponse>
}