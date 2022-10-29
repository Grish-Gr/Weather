package com.example.weather.usecases

import com.example.weather.model.ResultOf
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun getCurrentWeather(nameCity: String): ResultOf<CurrentForecastData> =
        weatherRepository.getCurrentForecast(nameCity)

    suspend fun getCurrentWeather(
        latitude: Float,
        longitude: Float
    ): ResultOf<CurrentForecastData> =
        weatherRepository.getCurrentForecast(latitude, longitude)
}