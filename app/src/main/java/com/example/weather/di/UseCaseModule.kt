package com.example.weather.di

import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.usecases.GetCurrentWeatherUseCase
import com.example.weather.usecases.GetLongIntervalWeatherUseCase
import com.example.weather.usecases.GetShortIntervalWeatherUseCase
import com.example.weather.usecases.SearchCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetCurrentWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetCurrentWeatherUseCase =
        GetCurrentWeatherUseCase(weatherRepository)

    @Provides
    fun provideGetShortIntervalWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetShortIntervalWeatherUseCase =
        GetShortIntervalWeatherUseCase(weatherRepository)

    @Provides
    fun provideGetLongIntervalWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetLongIntervalWeatherUseCase =
        GetLongIntervalWeatherUseCase(weatherRepository)

    @Provides
    fun provideSearchCityUseCase(
        geocodingRepository: GeocodingRepository
    ): SearchCityUseCase =
        SearchCityUseCase(geocodingRepository)
}