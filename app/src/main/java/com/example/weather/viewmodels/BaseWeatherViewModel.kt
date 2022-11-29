package com.example.weather.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

typealias ErrorAction = (ex: Exception) -> Unit
typealias SuccessAction<T> = (result: ResultOf.Success<T>) -> Unit

open class BaseWeatherViewModel: ViewModel() {

    protected var defaultErrorAction: ErrorAction = { ex ->
        Log.e(this::class.simpleName, ex.message.toString())
    }

    protected fun <T> manipulateResult(
        resultOf: ResultOf<T>,
        successAction: SuccessAction<T>,
        errorAction: ErrorAction
    ) {
        when (resultOf){
            is ResultOf.Success -> {
                successAction(resultOf)
            }
            is ResultOf.Error -> {
                viewModelScope.launch(Dispatchers.Main) {
                    errorAction(resultOf.exception)
                }
            }
        }
    }
}