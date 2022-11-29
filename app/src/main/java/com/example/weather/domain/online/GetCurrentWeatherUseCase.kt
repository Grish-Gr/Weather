package com.example.weather.domain.online

import com.example.weather.model.ResultOf
import com.example.weather.model.data.ForecastData
import com.example.weather.model.interfaces.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getCurrentWeather(
        latitude: Float,
        longitude: Float
    ): ResultOf<ForecastData> =
        weatherRepository.getCurrentForecast(latitude, longitude)
}