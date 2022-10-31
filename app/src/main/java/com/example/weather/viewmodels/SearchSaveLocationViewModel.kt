package com.example.weather.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weather.usecases.CheckInternetConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchSaveLocationViewModel @Inject constructor(
    private val internetConnection: CheckInternetConnectionUseCase
): ViewModel() {

    fun checkInternetConnection(context: Context): Boolean =
        internetConnection.checkConnection(context)
}