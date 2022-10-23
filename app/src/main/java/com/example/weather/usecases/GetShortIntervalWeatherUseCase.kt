package com.example.weather.usecases

import com.example.weather.model.Result
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetShortIntervalWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getShortIntervalWeather(
        nameCity: String,
        count: Int = 5
    ): Result<List<StepForecastData>> =
        weatherRepository.getStepForecast(nameCity, count)

    suspend fun getShortIntervalWeather(
        latitude: Float,
        longitude: Float,
        count: Int = 5
    ): Result<List<StepForecastData>> =
        weatherRepository.getStepForecast(latitude, longitude, count)
}