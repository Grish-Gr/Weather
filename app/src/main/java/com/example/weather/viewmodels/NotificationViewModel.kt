package com.example.weather.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weather.domain.utils.AssignWeatherNotificationUseCase
import com.example.weather.domain.utils.DeleteWeatherNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val assignWeatherNotificationUseCase: AssignWeatherNotificationUseCase,
    private val deleteWeatherNotificationUseCase: DeleteWeatherNotificationUseCase
): ViewModel() {

    fun assignNotification(intervalNotificationByHour: Int, isFeelsLike: Boolean, isDescription: Boolean){
        assignWeatherNotificationUseCase.assignNotification(
            intervalNotificationByMinutes = intervalNotificationByHour * 60L,
            isFeelsLike = isFeelsLike,
            isDescription = isDescription
        )
    }

    fun deleteAllNotification(){
        deleteWeatherNotificationUseCase.deleteAllNotification()
    }
}