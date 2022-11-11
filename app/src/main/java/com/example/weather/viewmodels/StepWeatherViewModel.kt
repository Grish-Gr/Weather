package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.StepForecastData
import com.example.weather.usecases.online.GetLongIntervalWeatherUseCase
import com.example.weather.usecases.utils.GetLastLocationUseCase
import com.example.weather.usecases.online.GetShortIntervalWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun setErrorAction(errorAction: ErrorAction){
        defaultErrorAction = errorAction
    }

    fun getShortListStepWeather(
        nameCity: String = lastLocation.getLastLocation().locationName,
        count: Int,
        errorAction: ErrorAction = defaultErrorAction
    ){
        viewModelScope.launch {
            val result = shortIntervalWeather.getShortIntervalWeather(nameCity, count)
            manipulateResult(
                resultOf = result,
                errorAction = {
                    _listStepWeather.postValue(emptyList())
                    errorAction(it)
                },
                successAction = {
                    _listStepWeather.postValue(it.value)
                })
        }
    }

    fun getShortListStepWeather(
        latitude: Float = lastLocation.getLastLocation().latitude,
        longitude: Float = lastLocation.getLastLocation().longitude,
        count: Int,
        errorAction: ErrorAction = defaultErrorAction
    ){
        viewModelScope.launch {
            val result = shortIntervalWeather.getShortIntervalWeather(latitude, longitude, count)
            manipulateResult(
                resultOf = result,
                errorAction = errorAction,
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
        viewModelScope.launch {
            val result = longIntervalWeather.getLongIntervalWeather(latitude, longitude)
            manipulateResult(
                resultOf = result,
                errorAction = errorAction,
                successAction = {
                    _listStepWeather.postValue(it.value)
                })
        }
    }
}