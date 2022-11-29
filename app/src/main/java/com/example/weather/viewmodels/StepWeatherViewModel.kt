package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.StepForecastData
import com.example.weather.domain.online.GetLongIntervalWeatherUseCase
import com.example.weather.domain.utils.GetLastLocationUseCase
import com.example.weather.domain.online.GetShortIntervalWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepWeatherViewModel @Inject constructor(
    private val shortIntervalWeather: GetShortIntervalWeatherUseCase,
    private val longIntervalWeather: GetLongIntervalWeatherUseCase,
    private val lastLocation: GetLastLocationUseCase
): BaseWeatherViewModel() {

    private val _listStepWeather = MutableLiveData<List<StepForecastData>>()
    val listStepWeather: LiveData<List<StepForecastData>> = _listStepWeather

    fun getShortListStepWeather(
        latitude: Float = lastLocation.getLastLocation().latitude,
        longitude: Float = lastLocation.getLastLocation().longitude,
        count: Int,
        errorAction: ErrorAction = defaultErrorAction
    ){
        viewModelScope.launch(Dispatchers.IO) {
            manipulateResult(
                resultOf = shortIntervalWeather.getShortIntervalWeather(latitude, longitude, count),
                errorAction = {
                    errorAction(it)
                    _listStepWeather.postValue(emptyList())
                },
                successAction = {
                    _listStepWeather.postValue(it.value)
                })
        }
    }

    fun getAllListStepWeather(
        latitude: Float = lastLocation.getLastLocation().latitude,
        longitude: Float = lastLocation.getLastLocation().longitude,
        errorAction: ErrorAction = defaultErrorAction
    ){
        viewModelScope.launch(Dispatchers.IO) {
            manipulateResult(
                resultOf = longIntervalWeather.getLongIntervalWeather(latitude, longitude),
                errorAction = errorAction,
                successAction = {
                    _listStepWeather.postValue(it.value)
                })
        }
    }
}