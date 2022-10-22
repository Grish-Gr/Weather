package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class InfoWeather(

    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val main: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("icon")
    val idIconWeather: String
)