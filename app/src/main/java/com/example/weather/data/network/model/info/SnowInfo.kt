package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class SnowInfo(
    @SerializedName("1h")
    val volumeSnowHour: Float? = null,

    @SerializedName("3h")
    val volumeSnow: Float? = null
)