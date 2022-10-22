package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class RainCurrentInfo(

    @SerializedName("1h")
    val volumeRain: Float
)