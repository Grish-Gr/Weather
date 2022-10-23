package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class CurrentSysInfo(

    @SerializedName("type")
    val type: Int,

    @SerializedName("id")
    val id: Long,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("country")
    val country: String,

    @SerializedName("sunrise")
    val timeToSunrise: Long,

    @SerializedName("sunset")
    val timeToSunset: Long
)