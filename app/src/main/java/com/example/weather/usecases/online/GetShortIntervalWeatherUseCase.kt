package com.example.weather.usecases.online

import com.example.weather.model.ResultOf
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetShortIntervalWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getShortIntervalWeather(
        nameCity: String,
        count: Int
    ): ResultOf<List<StepForecastData>> =
        weatherRepository.getStepForecast(nameCity, count)

    suspend fun getShortIntervalWeather(
        latitude: Float,
        longitude: Float,
        count: Int
    ): ResultOf<List<StepForecastData>> =
        weatherRepository.getStepForecast(latitude, longitude, count)
}