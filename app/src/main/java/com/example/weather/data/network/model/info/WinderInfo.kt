package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class WinderInfo(

    @SerializedName("speed")
    val speed: Float,

    @SerializedName("deg")
    val directionWind: Int,

    @SerializedName("gust")
    val gustWind: Float
)