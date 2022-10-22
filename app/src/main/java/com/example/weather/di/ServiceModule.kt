package com.example.weather.di

import androidx.lifecycle.ViewModel
import com.example.weather.data.network.GeocodingService
import com.example.weather.data.network.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    private const val BASE_URL = "http://api.openweathermap.org"
    private const val API_KEY = "c750f6c1fbc97349d8e7e197f08487a6"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()

                val httpUrl = originalRequest.url.newBuilder()
                    .addQueryParameter("appid", API_KEY)
                    .build()

                val request = originalRequest.newBuilder().url(httpUrl).build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideGeocodingService(retrofit: Retrofit): GeocodingService =
        retrofit.create(GeocodingService::class.java)

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}