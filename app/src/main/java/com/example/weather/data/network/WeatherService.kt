package com.example.weather.data.network

import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.StepForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService{

    @GET("./data/2.5/forecast")
    suspend fun getForecastThreeHours(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "metric"
    ): StepForecastResponse

    @GET("/data/2.5/forecast")
    suspend fun getForecastThreeHours(
        @Query("q") nameCity: String,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "metric"
    ): StepForecastResponse

    @GET("./data/2.5/weather")
    suspend fun getCurrentForecast(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "metric"
    ): CurrentForecastResponse

    @GET("./data/2.5/weather")
    suspend fun getCurrentForecast(
        @Query("q") nameCity: String,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "metric"
    ): CurrentForecastResponse
}