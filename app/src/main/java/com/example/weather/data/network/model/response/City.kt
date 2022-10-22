package com.example.weather.data.network.model.response

import com.example.weather.data.network.model.info.CoordinationInfo
import com.google.gson.annotations.SerializedName

data class City(

    @SerializedName("id")
    val idCity: Int,

    @SerializedName("name")
    val nameCity: String,

    @SerializedName("coord")
    val coordination: CoordinationInfo,

    @SerializedName("country")
    val country: String,

    @SerializedName("population")
    val population: Int,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("sunrise")
    val sunriseTime: Long,

    @SerializedName("sunset")
    val sunsetTime: Long
)