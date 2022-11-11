package com.example.weather.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.LocationData
import com.example.weather.usecases.offline.CheckSavedLocationUseCase
import com.example.weather.usecases.offline.SaveForecastUseCase
import com.example.weather.usecases.utils.SaveLastLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val saveLocation: SaveLastLocationUseCase,
    private val checkSavedLocationUseCase: CheckSavedLocationUseCase,
    private val saveForecast: SaveForecastUseCase
): BaseWeatherViewModel() {

    private val _chooseLocation = MutableLiveData<LocationData>()
    val chooseLocation = _chooseLocation

    fun setLocation(location: LocationData){
        viewModelScope.launch {
            if (!checkSavedLocationUseCase.isSavedLocation(location.latitude, location.longitude))
                saveForecast.saveLocation(location)
        }
        saveLocation.saveLocation(location)
        _chooseLocation.value = location
    }
}