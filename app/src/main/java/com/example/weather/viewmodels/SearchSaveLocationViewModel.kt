package com.example.weather.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.usecases.offline.DeleteSavedLocationUseCase
import com.example.weather.usecases.offline.SearchSavedLocationUseCase
import com.example.weather.usecases.utils.CheckInternetConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSaveLocationViewModel @Inject constructor(
    private val internetConnection: CheckInternetConnectionUseCase,
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
            Log.e("TAG", nameCity)
            Log.e("TAG", searchSavedLocationUseCase.searchLocation(nameCity).toString())
            _listSavedForecast.postValue(searchSavedLocationUseCase.searchLocation(nameCity))
        }
    }

    fun getLocations(){
        viewModelScope.launch {
            _listSavedForecast.postValue(searchSavedLocationUseCase.getAllLocation())
        }
    }

    fun checkInternetConnection(): Boolean =
        internetConnection.checkConnection()

    fun deleteLocation(location: LocationData){
        viewModelScope.launch {
            deleteSavedLocationUseCase.deleteLocation(location)
        }
    }
}