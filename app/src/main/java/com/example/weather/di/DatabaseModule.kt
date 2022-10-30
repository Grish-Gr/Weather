package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.data.db.LocationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): LocationDatabase {
        return Room.databaseBuilder(context, LocationDatabase::class.java, "location_database").build()
    }
}