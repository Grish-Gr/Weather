package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.data.LocationData
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {

    private val _currentLocation = MutableLiveData<LocationData>()
    val currentLocation: LiveData<LocationData> = _currentLocation

    fun updateCurrentLocation(location: LocationData){
        _currentLocation.value = location
    }

    fun refreshCurrentLocation(){
        _currentLocation.value = _currentLocation.value
    }
}