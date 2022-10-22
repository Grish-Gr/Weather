package com.example.weather.data.network.model.response

import com.google.gson.annotations.SerializedName

data class StepForecastResponse(
    @SerializedName("cod")
    val codeResponse: String,

    @SerializedName("message")
    val message: Int,

    @SerializedName("cnt")
    val count: Int,

    @SerializedName("list")
    val listForecast: List<StepForecast>,

    @SerializedName("city")
    val city: City
)