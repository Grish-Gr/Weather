package com.example.weather.usecases.online

import com.example.weather.model.ResultOf
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetLongIntervalWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getLongIntervalWeather(
        nameCity: String
    ): ResultOf<List<StepForecastData>> =
        weatherRepository.getStepForecast(nameCity)

    suspend fun getLongIntervalWeather(
        latitude: Float,
        longitude: Float
    ): ResultOf<List<StepForecastData>> =
        weatherRepository.getStepForecast(latitude, longitude)
}