package com.example.weather.usecases

import com.example.weather.model.Result
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetLongIntervalWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getShortIntervalWeather(
        nameCity: String
    ): Result<List<StepForecastData>> =
        weatherRepository.getStepForecast(nameCity, 40)

    suspend fun getShortIntervalWeather(
        latitude: Float,
        longitude: Float
    ): Result<List<StepForecastData>> =
        weatherRepository.getStepForecast(latitude, longitude, 40)
}