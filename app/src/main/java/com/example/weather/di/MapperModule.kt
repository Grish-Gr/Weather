package com.example.weather.di

import com.example.weather.data.db.entities.LocationEntity
import com.example.weather.data.db.entities.WeatherEntity
import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.LocationResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.mappers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun provideMapperCurrentForecast(): Mapper<CurrentForecastResponse, CurrentForecastData> =
        MapperCurrentForecast()

    @Provides
    fun provideMapperStepForecast(): Mapper<StepForecast, StepForecastData> =
        MapperStepForecast()

    @Provides
    fun provideMapperLocation(): Mapper<LocationResponse, LocationData> =
        MapperLocation()

    @Provides
    fun provideMapperSavedLocation(): Mapper<LocationEntity, LocationData> =
        MapperSavedLocation()

    @Provides
    fun provideMapperSavedForecast(
        mapperLocation: Mapper<LocationEntity, LocationData>
    ): Mapper<Pair<LocationEntity, WeatherEntity>, SavedForecastData> =
        MapperSavedForecast(mapperLocation)

    @Provides
    fun provideMapperEntityWeather(): Mapper<Pair<LocationData, CurrentForecastData>, WeatherEntity> =
        MapperWeatherEntity()

    @Provides
    fun provideMapperLocationEntity(): Mapper<LocationData, LocationEntity> =
        MapperLocationEntity()
}
