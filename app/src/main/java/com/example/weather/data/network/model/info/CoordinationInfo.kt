package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class CoordinationInfo(

    @SerializedName("lat")
    val latitude: Float,

    @SerializedName("lon")
    val longitude: Float,
)