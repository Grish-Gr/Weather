package com.example.weather.model

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out R>(val value: R): Result<R>()
    data class Error(val exceptionMessage: String): Result<Nothing>()
}