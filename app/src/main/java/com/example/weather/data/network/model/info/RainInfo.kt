package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class RainInfo(

    @SerializedName("3h")
    val volumeRain: Float
)