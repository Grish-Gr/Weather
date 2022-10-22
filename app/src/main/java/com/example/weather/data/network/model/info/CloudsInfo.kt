package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class CloudsInfo(

    @SerializedName("all")
    val cloudiness: Int
)