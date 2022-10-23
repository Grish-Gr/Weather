package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class RainInfo(
    @SerializedName("1h")
    val volumeRainHour: Float? = null,

    @SerializedName("3h")
    val volumeRain: Float? = null
)