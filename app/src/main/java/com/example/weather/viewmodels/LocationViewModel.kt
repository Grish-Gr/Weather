package com.example.weather.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.data.LocationData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(

): ViewModel() {

    private val _chooseLocation = MutableLiveData<LocationData>()
    val chooseLocation = _chooseLocation

    fun setLocation(location: LocationData){
        _chooseLocation.value = location
    }
}