package com.example.weather.data.network.model.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("local_names")
    val countriesName: Map<String, String>? = null,

    @SerializedName("lat")
    val latitude: Float,

    @SerializedName("lon")
    val longitude: Float,

    @SerializedName("country")
    val country: String,

    @SerializedName("state")
    val state: String? = null
)