package com.example.weather.di

import com.example.weather.data.db.LocationDatabase
import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.data.network.GeocodingService
import com.example.weather.data.network.WeatherService
import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.LocationResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.GeocodingRepositoryImpl
import com.example.weather.model.SaveLocationRepositoryImpl
import com.example.weather.model.WeatherRepositoryImpl
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.interfaces.GeocodingRepository
import com.example.weather.model.interfaces.SaveLocationRepository
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.model.mappers.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideSaveLocationRepository(
        locationDatabase: LocationDatabase,
        mapperForecast: Mapper<Pair<LocationEntity, WeatherEntity>, SavedForecastData>,
        mapperEntity: Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity>
    ): SaveLocationRepository{
        return SaveLocationRepositoryImpl(locationDatabase, mapperForecast, mapperEntity)
    }
}