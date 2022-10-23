package com.example.weather.model

import android.util.Log
import com.example.weather.data.network.WeatherService
import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.TemperatureData
import com.example.weather.model.data.WeatherData
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.model.mappers.Mapper
import com.example.weather.model.mappers.MapperCurrentForecast
import com.example.weather.model.mappers.MapperStepForecast
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val mapperCurrentForecast: Mapper<CurrentForecastResponse, CurrentForecastData>,
    private val mapperStepForecast: Mapper<StepForecast, StepForecastData>
) : WeatherRepository {

    override suspend fun getCurrentForecast(
        latitude: Float,
        longitude: Float
    ): Result<CurrentForecastData> {
        return try{
            Result.Success(mapperCurrentForecast.mapping(weatherService.getCurrentForecast(latitude, longitude)))
        } catch (ex: Exception){
            Log.e(this::class.toString(), ex.toString())
            Result.Error(ex.toString())
        }
    }

    override suspend fun getCurrentForecast(nameCity: String): Result<CurrentForecastData> {
        return try {
            Result.Success(mapperCurrentForecast.mapping(weatherService.getCurrentForecast(nameCity)))
        } catch (ex: Exception){
            Log.e(this::class.toString(), ex.toString())
            Result.Error(ex.toString())
        }
    }

    override suspend fun getStepForecast(
        latitude: Float,
        longitude: Float,
        count: Int
    ): Result<List<StepForecastData>>{
        return try {
            Result.Success(weatherService.getForecastThreeHours(
                latitude = latitude,
                longitude = longitude,
                count = count
            ).listForecast.map { stepForecast ->
                mapperStepForecast.mapping(stepForecast)
            })
        } catch (ex: Exception){
            Log.e(this::class.simpleName, ex.toString())
            Result.Error(ex.toString())
        }
    }

    override suspend fun getStepForecast(nameCity: String, count: Int): Result<List<StepForecastData>> {
        return try {
            Result.Success(weatherService.getForecastThreeHours(nameCity, count).listForecast.map { stepForecast ->
                mapperStepForecast.mapping(stepForecast)
            })
        } catch (ex: Exception){
            Log.e(WeatherRepositoryImpl::class.simpleName, ex.toString())
            Result.Error(ex.toString())
        }
    }
}