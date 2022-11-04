package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.data.LocationData
import com.example.weather.usecases.utils.GetLastLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentLocationViewModel @Inject constructor(
    lastLocation: GetLastLocationUseCase
): ViewModel() {

    private val _currentLocation = MutableLiveData<LocationData>(lastLocation.getLastLocation())
    val currentLocation: LiveData<LocationData> = _currentLocation

    fun updateCurrentLocation(location: LocationData){
        _currentLocation.value = location
    }

    fun refreshCurrentLocation(){
        _currentLocation.value = _currentLocation.value
    }
}