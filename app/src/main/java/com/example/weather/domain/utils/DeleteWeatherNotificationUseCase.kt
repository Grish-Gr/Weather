package com.example.weather.domain.utils

import android.content.Context
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeleteWeatherNotificationUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun deleteAllNotification(){
        WorkManager.getInstance(context).cancelAllWork()
    }
}