package com.example.weather.di

import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.interfaces.SaveLocationRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.usecases.*
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
    fun provideCheckInternetConnectionUseCase(): CheckInternetConnectionUseCase =
        CheckInternetConnectionUseCase()

    @Provides
    fun provideSaveForecastUseCase(
        saveLocationRepository: SaveLocationRepository
    ): SaveForecastUseCase =
        SaveForecastUseCase(saveLocationRepository)

    @Provides
    fun provideUpdateSavedForecast(
        saveLocationRepository: SaveLocationRepository
    ): UpdateSavedForecastUseCase =
        UpdateSavedForecastUseCase(saveLocationRepository)

    @Provides
    fun provideSearchSavedLocationUseCase(
        saveLocationRepository: SaveLocationRepository
    ): SearchSavedLocationUseCase =
        SearchSavedLocationUseCase(saveLocationRepository)

    @Provides
    fun provideDeleteSavedLocationUseCase(
        saveLocationRepository: SaveLocationRepository
    ): DeleteSavedLocationUseCase =
        DeleteSavedLocationUseCase(saveLocationRepository)
}