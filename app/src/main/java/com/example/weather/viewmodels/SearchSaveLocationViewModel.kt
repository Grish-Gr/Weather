package com.example.weather.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weather.usecases.utils.CheckInternetConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchSaveLocationViewModel @Inject constructor(
    private val internetConnection: CheckInternetConnectionUseCase
): ViewModel() {

    fun checkInternetConnection(): Boolean =
        internetConnection.checkConnection()
}