package com.example.weather.di

import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.data.network.model.response.LocationResponse
import com.example.weather.data.network.model.response.StepForecast
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.mappers.Mapper
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import com.example.weather.model.mappers.MapperCurrentForecast
import com.example.weather.model.mappers.MapperLocation
import com.example.weather.model.mappers.MapperStepForecast
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
}
