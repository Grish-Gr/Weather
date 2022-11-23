package com.example.weather.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.usecases.offline.GetSavedForecastUseCase
import com.example.weather.usecases.offline.UpdateSavedForecastUseCase
import com.example.weather.usecases.online.GetCurrentWeatherUseCase
import com.example.weather.usecases.utils.CheckInternetConnectionUseCase
import com.example.weather.usecases.utils.GetLastLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val currentWeather: GetCurrentWeatherUseCase,
    private val internetConnectionUseCase: CheckInternetConnectionUseCase,
    private val updateSavedForecastUseCase: UpdateSavedForecastUseCase,
    private val getSaveForecastUseCase: GetSavedForecastUseCase,
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
        getCurrentForecastByService(latitude, longitude, errorAction)

    }

    private fun getCurrentForecastByService(
        latitude: Float,
        longitude: Float,
        errorAction: ErrorAction
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            manipulateResult(
                resultOf = currentWeather.getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude
                ),
                errorAction = {Log.e("TAG", "___ERROR___")},
                successAction = { forecast ->
                    _currentForecast.postValue(forecast.value)
                    viewModelScope.launch(Dispatchers.IO) {
                        updateSavedForecastUseCase.updateSavedForecast(latitude, longitude, forecast.value)
                    }
                    changeTheme(forecast.value.date)
                }
            )
        }
    }

    private fun getCurrentForecastByDatabase(
        latitude: Float,
        longitude: Float
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedForecast = getSaveForecastUseCase.getSavedForecastData(
                latitude = latitude,
                longitude = longitude
            )
            _currentForecast.postValue(savedForecast)
            changeTheme(savedForecast.date)
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

    private fun checkInternetConnection(): Boolean =
        internetConnectionUseCase.checkConnection()

    private fun isNight(date: Date): Boolean{
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour in 22..24 || hour in 0..6
    }
}