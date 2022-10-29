package com.example.weather.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.ResultOf
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.usecases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: GetCurrentWeatherUseCase
): BaseWeatherViewModel() {

    private val _currentForecast = MutableLiveData<CurrentForecastData>()
    val currentForecast: LiveData<CurrentForecastData> = _currentForecast

    fun getCurrentForecast(nameCity: String, errorAction: ErrorAction = defaultErrorAction){
        viewModelScope.launch {
            manipulateResult(
                resultOf = currentWeatherUseCase.getCurrentWeather(nameCity),
                errorAction = errorAction,
                successAction = {
                    _currentForecast.postValue(it.value)
                }
            )
        }
    }

    fun getCurrentForecast(latitude: Float, longitude: Float, errorAction: ErrorAction = defaultErrorAction){
        viewModelScope.launch {
            manipulateResult(
                resultOf = currentWeatherUseCase.getCurrentWeather(latitude, longitude),
                errorAction = errorAction,
                successAction = {
                    _currentForecast.postValue(it.value)
                }
            )
        }
    }

}