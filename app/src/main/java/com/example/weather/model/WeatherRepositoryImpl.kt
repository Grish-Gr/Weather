package com.example.weather.model

import android.util.Log
import com.example.weather.data.network.WeatherService
import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.SavedWeatherRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.model.mappers.Mapper
import java.net.UnknownHostException
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val savedWeatherRepository: SavedWeatherRepository,
    private val mapperCurrentForecast: Mapper<CurrentForecastResponse, CurrentForecastData>,
    private val mapperStepForecast: Mapper<StepForecast, StepForecastData>
) : WeatherRepository {

    override suspend fun getCurrentForecast(
        latitude: Float,
        longitude: Float
    ): ResultOf<ForecastData> {
        return try{
            val forecast = mapperCurrentForecast
                .mapping(weatherService.getCurrentForecast(
                    latitude = latitude,
                    longitude = longitude,
                    language = Locale.getDefault().language))
            savedWeatherRepository.updateWeather(latitude, longitude, forecast)
            ResultOf.Success(forecast)
        } catch (ex: Exception){
            ResultOf.Success(savedWeatherRepository.getForecast(latitude, longitude))
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
                language = Locale.getDefault().language
            ).listForecast.map { stepForecast ->
                mapperStepForecast.mapping(stepForecast)
            }.filter { stepForecast ->
                val calendar = Calendar.getInstance()
                calendar.time = stepForecast.date
                return@filter calendar.get(Calendar.HOUR_OF_DAY) % 6 == 0
            }.subList(0, count))
        } catch (ex: Exception){
            ResultOf.Error(ex)
        }
    }

    override suspend fun getStepForecast(
        latitude: Float,
        longitude: Float
    ): ResultOf<List<StepForecastData>> {
        return try {
            ResultOf.Success(weatherService.getForecastThreeHours(
                latitude = latitude,
                longitude = longitude,
                language = Locale.getDefault().language
            ).listForecast.map { stepForecast ->
                mapperStepForecast.mapping(stepForecast)
            }.filter { stepForecast ->
                val calendar = Calendar.getInstance()
                calendar.time = stepForecast.date
                return@filter calendar.get(Calendar.HOUR_OF_DAY) % 6 == 0
            })
        } catch (ex: Exception){
            Log.e(WeatherRepositoryImpl::class.simpleName, ex.toString())
            ResultOf.Error(ex)
        }
    }
}