package com.example.weather.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.domain.online.GetCurrentWeatherUseCase
import com.example.weather.domain.utils.GetLastLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val currentWeather: GetCurrentWeatherUseCase,
    private val lastLocation: GetLastLocationUseCase
): BaseWeatherViewModel() {

    private val _currentForecast = MutableLiveData<ForecastData>()
    val currentForecast: LiveData<ForecastData> = _currentForecast

    fun getLastLocation(): LocationData = lastLocation.getLastLocation()

    fun getCurrentForecast(
        latitude: Float = lastLocation.getLastLocation().latitude,
        longitude: Float = lastLocation.getLastLocation().longitude,
        errorAction: ErrorAction = defaultErrorAction
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            manipulateResult(
                resultOf = currentWeather.getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude
                ),
                errorAction = errorAction,
                successAction = { forecast ->
                    _currentForecast.postValue(forecast.value)
                    changeTheme(forecast.value.date)
                }
            )
        }
    }

    private fun changeTheme(date: Date){
        viewModelScope.launch(Dispatchers.Main) {
            Log.e("TAG", "Change Theme")
            if (isNight(date)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun isNight(date: Date): Boolean{
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour in 22..24 || hour in 0..6
    }
}