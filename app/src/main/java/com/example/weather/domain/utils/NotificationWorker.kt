package com.example.weather.domain.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weather.R
import com.example.weather.domain.online.GetCurrentWeatherUseCase
import com.example.weather.model.ResultOf
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.detail.TemperatureDetail
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getLastLocationUseCase: GetLastLocationUseCase
): CoroutineWorker(context, workerParameters){

    private val isDescription = inputData.getBoolean(IS_DESCRIPTION_WEATHER_IN_NOTIFICATION, true)
    private val isFeelsLike = inputData.getBoolean(IS_FEELS_LIKE_TEMP_IN_NOTIFICATION, false)

    override suspend fun doWork(): Result {
        val location = getLastLocationUseCase.getLastLocation()
        val result = getCurrentWeatherUseCase.getCurrentWeather(
            latitude = location.latitude,
            longitude = location.longitude)

        val weather = if (result is ResultOf.Success && result.value is CurrentForecastData) {
            result.value
        } else return Result.retry()

        val builder = getBuilderNotification(weather, location)
        val notificationManager = NotificationManagerCompat.from(this.applicationContext)
        setNotificationInChannel(notificationManager)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
        return Result.success()
    }

    private fun getBuilderNotification(forecast: CurrentForecastData, location: LocationData): NotificationCompat.Builder {
        return NotificationCompat.Builder(this.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(location.locationName)
            .setContentText(getContentTextNotification(forecast))
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                getContentAllTextNotification(forecast)
            ))
            .setPriority(NotificationCompat.PRIORITY_LOW)
    }

    private fun getContentTextNotification(forecast: CurrentForecastData): String{
        val content = StringBuilder()
        content.append(applicationContext.getText(R.string.description_current_temp))
        content.append(String.format(": %s", TemperatureDetail.getTemperatureCelsius(forecast.temperature.temperature)))
        return content.toString()
    }

    private fun getContentAllTextNotification(forecast: CurrentForecastData): String{
        val content = StringBuilder(getContentTextNotification(forecast))
        if (isFeelsLike){
            content.append("\n")
            content.append(applicationContext.getText(R.string.description_feels_like))
            content.append(String.format(": %s",
                TemperatureDetail.getTemperatureCelsius(forecast.temperature.feelsLikeTemperature)
            ))
        }
        if (isDescription){
            content.append("\n")
            content.append(forecast.weather.descriptionWeather)
        }
        return content.toString();
    }

    private fun setNotificationInChannel(notificationManager: NotificationManagerCompat){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, NAME_CHANNEL,
                NotificationManager.IMPORTANCE_LOW
            )
            channel.description = applicationContext.getText(R.string.description_notification_channel).toString()
            channel.enableLights(false)
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object{
        const val IS_DESCRIPTION_WEATHER_IN_NOTIFICATION = "KeyDescriptionWeather"
        const val IS_FEELS_LIKE_TEMP_IN_NOTIFICATION = "KeyFeelsLikeTemp"
        private const val NAME_CHANNEL = "Weather"
        private const val CHANNEL_ID = "NotificationId"
        private const val NOTIFICATION_ID = 1
    }
}