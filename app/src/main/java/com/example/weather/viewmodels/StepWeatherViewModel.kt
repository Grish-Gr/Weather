package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.StepForecastData
import com.example.weather.usecases.GetShortIntervalWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepWeatherViewModel @Inject constructor(
    private val shortIntervalWeather: GetShortIntervalWeatherUseCase
): BaseWeatherViewModel() {

    private val _listStepWeather = MutableLiveData<List<StepForecastData>>()
    val listStepWeather: LiveData<List<StepForecastData>> = _listStepWeather

    fun getListStepWeather(
        nameCity: String,
        count: Int = 5,
        errorAction: ErrorAction = defaultErrorAction
    ){
        viewModelScope.launch {
            val result = shortIntervalWeather.getShortIntervalWeather(nameCity, count)
            manipulateResult(
                resultOf = result,
                errorAction = errorAction,
                successAction = {
                    _listStepWeather.postValue(it.value)
                })
        }
    }

    fun getListStepWeather(
        latitude: Float,
        longitude: Float,
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
}