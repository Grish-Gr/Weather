package com.example.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.offline.DeleteSavedLocationUseCase
import com.example.weather.domain.offline.SearchSavedLocationUseCase
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSaveLocationViewModel @Inject constructor(
    private val searchSavedLocationUseCase: SearchSavedLocationUseCase,
    private val deleteSavedLocationUseCase: DeleteSavedLocationUseCase
): BaseWeatherViewModel() {

    private var jobSearchLocation: Job? = null
    private val _listSavedForecast = MutableLiveData<List<SavedForecastData>>()
    val listSavedForecast: LiveData<List<SavedForecastData>> = _listSavedForecast

    fun getLocations(nameCity: String){
        jobSearchLocation?.cancel()
        jobSearchLocation = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            _listSavedForecast.postValue(searchSavedLocationUseCase.searchLocation(nameCity))
        }
    }

    fun getLocations(){
        viewModelScope.launch {
            _listSavedForecast.postValue(searchSavedLocationUseCase.getAllLocation())
        }
    }

    fun deleteLocation(location: LocationData){
        viewModelScope.launch {
            deleteSavedLocationUseCase.deleteLocation(location)
        }
    }
}