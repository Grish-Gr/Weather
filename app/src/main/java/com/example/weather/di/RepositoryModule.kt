package com.example.weather.di

import android.content.Context
import com.example.weather.data.db.LocationDatabase
import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.data.network.GeocodingService
import com.example.weather.data.network.WeatherService
import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.LocationResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.GeocodingRepositoryImpl
import com.example.weather.model.SavedWeatherRepositoryImpl
import com.example.weather.model.SharedPreferencesRepositoryImpl
import com.example.weather.model.WeatherRepositoryImpl
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.interfaces.SavedWeatherRepository
import com.example.weather.model.interfaces.SharedPreferencesRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.model.mappers.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        weatherService: WeatherService,
        mapperCurrentForecast: Mapper<CurrentForecastResponse, CurrentForecastData>,
        mapperStepForecast: Mapper<StepForecast, StepForecastData>
    ): WeatherRepository{
        return WeatherRepositoryImpl(weatherService, mapperCurrentForecast, mapperStepForecast)
    }

    @Provides
    fun provideGeocodingRepository(
        geocodingService: GeocodingService,
        mapperLocation: Mapper<LocationResponse, LocationData>
    ): GeocodingRepository{
        return GeocodingRepositoryImpl(geocodingService, mapperLocation)
    }

    @Provides
    fun provideSavedWeatherRepository(
        locationDatabase: LocationDatabase,
        mapperForecast: Mapper<Pair<LocationEntity, WeatherEntity>, SavedForecastData>,
        mapperWeatherEntity: Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity>,
        mapperLocation: Mapper<LocationEntity, LocationData>,
        mapperLocationEntity: Mapper<LocationData, LocationEntity>
    ): SavedWeatherRepository{
        return SavedWeatherRepositoryImpl(locationDatabase, mapperForecast, mapperWeatherEntity, mapperLocation, mapperLocationEntity)
    }

    @Provides
    fun provideSharedPreferencesRepository(
        @ApplicationContext context: Context
    ): SharedPreferencesRepository =
        SharedPreferencesRepositoryImpl(context)
}