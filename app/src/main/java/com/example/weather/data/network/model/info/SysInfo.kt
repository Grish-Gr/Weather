package com.example.weather.data.network.model.info

import com.google.gson.annotations.SerializedName

data class SysInfo(

    @SerializedName("pod")
    val partOfDay: String
)