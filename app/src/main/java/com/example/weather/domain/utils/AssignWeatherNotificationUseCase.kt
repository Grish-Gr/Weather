package com.example.weather.domain.utils

import android.content.Context
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AssignWeatherNotificationUseCase @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {

    fun assignNotification(intervalNotificationByMinutes: Long, isFeelsLike: Boolean, isDescription: Boolean){
        WorkManager.getInstance(applicationContext).cancelAllWork()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val data = getInputData(isFeelsLike, isDescription)
        val uploadWorkRequest: PeriodicWorkRequest = PeriodicWorkRequest
            .Builder(NotificationWorker::class.java,
                intervalNotificationByMinutes, TimeUnit.MINUTES,
                intervalNotificationByMinutes.minus(15), TimeUnit.MINUTES)
            .setInputData(data)
            .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, uploadWorkRequest)
    }

    private fun getInputData(isFeelsLike: Boolean, isDescription: Boolean): Data = Data.Builder()
        .putBoolean(NotificationWorker.IS_DESCRIPTION_WEATHER_IN_NOTIFICATION, isDescription)
        .putBoolean(NotificationWorker.IS_FEELS_LIKE_TEMP_IN_NOTIFICATION, isFeelsLike)
        .build()

    private companion object{
        const val WORK_NAME = "WeatherNotification"
    }
}