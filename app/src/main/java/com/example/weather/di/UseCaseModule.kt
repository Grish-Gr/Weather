package com.example.weather.di

import android.content.Context
import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.interfaces.SavedWeatherRepository
import com.example.weather.model.interfaces.SharedPreferencesRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.usecases.offline.*
import com.example.weather.usecases.online.GetCurrentWeatherUseCase
import com.example.weather.usecases.online.GetLongIntervalWeatherUseCase
import com.example.weather.usecases.online.GetShortIntervalWeatherUseCase
import com.example.weather.usecases.online.SearchLocationUseCase
import com.example.weather.usecases.utils.CheckInternetConnectionUseCase
import com.example.weather.usecases.utils.GetLastLocationUseCase
import com.example.weather.usecases.utils.SaveLastLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

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
    fun provideCheckInternetConnectionUseCase(
        @ApplicationContext context: Context
    ): CheckInternetConnectionUseCase =
        CheckInternetConnectionUseCase(context)

    @Provides
    fun provideSaveForecastUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): SaveForecastUseCase =
        SaveForecastUseCase(savedWeatherRepository)

    @Provides
    fun provideUpdateSavedForecast(
        savedWeatherRepository: SavedWeatherRepository
    ): UpdateSavedForecastUseCase =
        UpdateSavedForecastUseCase(savedWeatherRepository)

    @Provides
    fun provideSearchSavedLocationUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): SearchSavedLocationUseCase =
        SearchSavedLocationUseCase(savedWeatherRepository)

    @Provides
    fun provideGetSavedForecastUseCase(
        savedWeatherRepository: SavedWeatherRepository
    ): GetSavedForecastUseCase =
        GetSavedForecastUseCase(savedWeatherRepository)

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