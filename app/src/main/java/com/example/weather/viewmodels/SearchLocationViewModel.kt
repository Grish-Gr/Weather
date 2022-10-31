package com.example.weather.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.LocationData
import com.example.weather.usecases.SearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(
    private val searchCity: SearchLocationUseCase
): BaseWeatherViewModel() {

    private var jobSearchLocation: Job? = null
    private val _listSearchingLocation = MutableLiveData<List<LocationData>>()
    val listSearchingLocation: LiveData<List<LocationData>> = _listSearchingLocation

    fun getLocation(nameCity: String){
        jobSearchLocation?.cancel()
        jobSearchLocation = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            Log.e("TAG", nameCity)
            manipulateResult(
                searchCity.searchCityByName(nameCity),
                successAction = { _listSearchingLocation.postValue(it.value) },
                errorAction = defaultErrorAction)
        }
    }
}