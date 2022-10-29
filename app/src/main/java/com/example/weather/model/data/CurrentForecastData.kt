package com.example.weather.model.data

import java.util.*

data class CurrentForecastData(
    val temperature: TemperatureData,
    val weather: WeatherData,
    private val sunTime: SunTimeData,
    private val timeZone: Int
){

    fun getCurrentDateByUTC(): Date{
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val millis = Calendar.getInstance().timeInMillis + (timeZone * 1000)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar.time
    }

    fun getSunriseDate(): Date =
        getDateByUTC(sunTime.timeToSunrise)

    fun getSunsetDate(): Date =
        getDateByUTC(sunTime.timeToSunset)

    private fun getDateByUTC(dateUtcInSecond: Long): Date{
        val millis = (dateUtcInSecond  + timeZone) * 1000
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar.time
    }
}