package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.online.SearchLocationUseCase
import com.example.weather.model.data.LocationData
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

    fun getLocation(nameCity: String, errorAction: ErrorAction = defaultErrorAction){
        jobSearchLocation?.cancel()
        jobSearchLocation = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            manipulateResult(
                resultOf = searchCity.searchCityByName(nameCity),
                successAction = { _listSearchingLocation.postValue(it.value) },
                errorAction = errorAction)
        }
    }
}