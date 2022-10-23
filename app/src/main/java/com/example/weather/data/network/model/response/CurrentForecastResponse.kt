package com.example.weather.data.network.model.response

import com.example.weather.data.network.model.info.*
import com.google.gson.annotations.SerializedName
import dagger.multibindings.IntoMap

data class CurrentForecastResponse(

    @SerializedName("coord")
    val coordination: CoordinationInfo,

    @SerializedName("weather")
    val listWeather: List<InfoWeather>,

    @SerializedName("base")
    val base: String,

    @SerializedName("main")
    val infoTemperature: TemperatureInfo,

    @SerializedName("visibility")
    val visibility: Int,

    @SerializedName("wind")
    val winder: WinderInfo,

    @SerializedName("rain")
    val rain: RainInfo? = null,

    @SerializedName("snow")
    val snow: SnowInfo? = null,

    @SerializedName("clouds")
    val clouds: CloudsInfo,

    @SerializedName("dt")
    val timeOfData: Long,

    @SerializedName("sys")
    val sys: CurrentSysInfo,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val cityName: String,

    @SerializedName("cod")
    val codeResponse: Int
)