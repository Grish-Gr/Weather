package com.example.weather.model

import android.util.Log
import com.example.weather.data.network.WeatherService
import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.model.mappers.Mapper
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val mapperCurrentForecast: Mapper<CurrentForecastResponse, CurrentForecastData>,
    private val mapperStepForecast: Mapper<StepForecast, StepForecastData>
) : WeatherRepository {

    override suspend fun getCurrentForecast(
        latitude: Float,
        longitude: Float
    ): ResultOf<CurrentForecastData> {
        return try{
            ResultOf.Success(mapperCurrentForecast
                .mapping(weatherService.getCurrentForecast(
                    latitude = latitude,
                    longitude = longitude,
                    language = Locale.getDefault().language)))
        } catch (ex: Exception){
            Log.e(this::class.toString(), ex.toString())
            ResultOf.Error(ex)
        }
    }

    override suspend fun getCurrentForecast(nameCity: String): ResultOf<CurrentForecastData> {
        return try {
            ResultOf.Success(mapperCurrentForecast
                .mapping(weatherService.getCurrentForecast(
                    nameCity = nameCity,
                    language = Locale.getDefault().language)))
        } catch (ex: Exception){
            Log.e(this::class.toString(), ex.toString())
            ResultOf.Error(ex)
        }
    }

    override suspend fun getStepForecast(
        latitude: Float,
        longitude: Float,
        count: Int
    ): ResultOf<List<StepForecastData>>{
        return try {
            ResultOf.Success(weatherService.getForecastThreeHours(
                latitude = latitude,
                longitude = longitude,
                count = count,
                language = Locale.getDefault().language
            ).listForecast.map { stepForecast ->
                mapperStepForecast.mapping(stepForecast)
            })
        } catch (ex: Exception){
            Log.e(this::class.simpleName, ex.toString())
            ResultOf.Error(ex)
        }
    }

    override suspend fun getStepForecast(nameCity: String, count: Int): ResultOf<List<StepForecastData>> {
        return try {
            ResultOf.Success(weatherService.getForecastThreeHours(
                nameCity = nameCity,
                count = count,
                language = Locale.getDefault().language
            ).listForecast.map { stepForecast ->
                mapperStepForecast.mapping(stepForecast)
            })
        } catch (ex: Exception){
            Log.e(WeatherRepositoryImpl::class.simpleName, ex.toString())
            ResultOf.Error(ex)
        }
    }
}