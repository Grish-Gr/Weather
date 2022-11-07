package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.StepForecastData
import com.example.weather.usecases.online.GetLongIntervalWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherStepViewModel @Inject constructor(
    private val getLongIntervalWeatherUseCase: GetLongIntervalWeatherUseCase
): BaseWeatherViewModel() {

    private val _listStepForecast = MutableLiveData<List<StepForecastData>>()
    val listStepForecast: LiveData<List<StepForecastData>> = _listStepForecast

    fun getAllForecastByInterval(location: LocationData){
        viewModelScope.launch(Dispatchers.IO) {
            manipulateResult(
                resultOf = getLongIntervalWeatherUseCase.getLongIntervalWeather(
                    latitude = location.latitude,
                    longitude = location.longitude
                ),
                successAction = {
                    _listStepForecast.postValue(it.value)
                },
                errorAction = defaultErrorAction
            )
        }
    }
}