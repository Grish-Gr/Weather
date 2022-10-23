package com.example.weather.data.network.model.response

import com.example.weather.data.network.model.info.*
import com.google.gson.annotations.SerializedName

data class StepForecast(
    @SerializedName("dt")
    val timeOfData: Long,

    @SerializedName("main")
    val infoTemperatures: TemperatureInfo,

    @SerializedName("weather")
    val weather: List<InfoWeather>,

    @SerializedName("clouds")
    val clouds: CloudsInfo,

    @SerializedName("wind")
    val winder: WinderInfo,

    @SerializedName("visibility")
    val visibility: Int,

    @SerializedName("pop")
    val probabilityOfPrecipitation: Float,

    @SerializedName("rain")
    val rain: RainInfo? = null,

    @SerializedName("snow")
    val snow: SnowInfo? = null,

    @SerializedName("sys")
    val sys: SysInfo,

    @SerializedName("dt_txt")
    val textDate: String
)