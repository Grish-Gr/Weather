package com.example.weather.model

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R & Any): ResultOf<R>()
    data class Error(val exceptionMessage: String): ResultOf<Nothing>()
}