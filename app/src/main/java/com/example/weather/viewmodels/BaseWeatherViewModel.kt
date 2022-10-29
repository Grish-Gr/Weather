package com.example.weather.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weather.model.ResultOf

typealias ErrorAction = (message: String) -> Unit
typealias SuccessAction<T> = (result: ResultOf.Success<T>) -> Unit

open class BaseWeatherViewModel: ViewModel() {

    protected val defaultErrorAction: ErrorAction = { Log.e("TAG", "Throw Exception")}

    protected fun <T> manipulateResult(
        resultOf: ResultOf<T>,
        successAction: SuccessAction<T>,
        errorAction: ErrorAction
    ) = when (resultOf){
        is ResultOf.Success -> {
            successAction(resultOf)
        }
        is ResultOf.Error -> {
            errorAction(resultOf.exceptionMessage)
        }
    }
}