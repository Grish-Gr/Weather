package com.example.weather.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weather.usecases.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val searchCity: SearchCityUseCase
): ViewModel() {

    fun getLocation(nameCity: String){

    }
}