package com.example.weather.di

import android.content.Context
import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.interfaces.SavedWeatherRepository
import com.example.weather.model.interfaces.SharedPreferencesRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.domain.offline.*
import com.example.weather.domain.online.GetCurrentWeatherUseCase
import com.example.weather.domain.online.GetLongIntervalWeatherUseCase
import com.example.weather.domain.online.GetShortIntervalWeatherUseCase
import com.example.weather.domain.online.SearchLocationUseCase
import com.example.weather.domain.utils.GetLastLocationUseCase
import com.example.weather.domain.utils.SaveLastLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
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
    ): SearchLocationUseCase =
        SearchLocationUseCase(geocodingRepository)

    @Provides
    fun provideSaveForecastUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): SaveForecastUseCase =
        SaveForecastUseCase(savedWeatherRepository)

    @Provides
    fun provideSearchSavedLocationUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): SearchSavedLocationUseCase =
        SearchSavedLocationUseCase(savedWeatherRepository)

    @Provides
    fun provideCheckSavedLocationUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): CheckSavedLocationUseCase =
        CheckSavedLocationUseCase(savedWeatherRepository)

    @Provides
    fun provideDeleteSavedLocationUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): DeleteSavedLocationUseCase =
        DeleteSavedLocationUseCase(savedWeatherRepository)

    @Provides
    fun provideGetLastLocationUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetLastLocationUseCase =
        GetLastLocationUseCase(sharedPreferencesRepository)

    @Provides
    fun provideSaveLastLocationUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SaveLastLocationUseCase =
        SaveLastLocationUseCase(sharedPreferencesRepository)
}