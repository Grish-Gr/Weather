package com.example.weather.usecases

import com.example.weather.model.Result
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getCurrentWeather(nameCity: String): Result<CurrentForecastData> =
        weatherRepository.getCurrentForecast(nameCity)

    suspend fun getCurrentWeather(
        latitude: Float,
        longitude: Float
    ): Result<CurrentForecastData> =
        weatherRepository.getCurrentForecast(latitude, longitude)
}