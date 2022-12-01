package com.example.weather.model.mappers

import com.example.weather.data.network.model.response.CurrentForecastResponse
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.detail.IconsDetail
import com.example.weather.model.data.detail.SunTimeDetail
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.model.data.detail.WeatherDetail
import java.util.*

class MapperCurrentForecast: Mapper<CurrentForecastResponse, CurrentForecastData> {
    override fun mapping(source: CurrentForecastResponse): CurrentForecastData {
        val temperatureData = TemperatureDetail(
            temperature = source.infoTemperature.temperature,
            feelsLikeTemperature = source.infoTemperature.feelsLike,
            minTemperature = source.infoTemperature.minTemperature,
            maxTemperature = source.infoTemperature.maxTemperature
        )
        val weatherData = WeatherDetail(
            pressure = source.infoTemperature.pressure,
            humidity = source.infoTemperature.humidity,
            descriptionWeather = source.listWeather.first().description.replaceFirstChar { it.uppercase() },
            cloudiness = source.clouds.cloudiness,
            visibility = source.visibility,
            speedWinder = source.winder.speed.toInt()
        )
        val sunTimeData = SunTimeDetail(
            dateSunrise = getDateByUTC(source.sys.timeToSunrise, source.timezone),
            dateSunset = getDateByUTC(source.sys.timeToSunset, source.timezone)
        )
        val icons = IconsDetail(
            urlIconWeather = getUrlByIcon(source.listWeather.first().idIconWeather),
            urlIconWeatherHeight = getUrlByIconHeight(source.listWeather.first().idIconWeather)
        )
        return CurrentForecastData(
            temperature = temperatureData,
            weather = weatherData,
            sunTime = sunTimeData,
            date = getCurrentDateByUTC(source.timezone),
            icons = icons
        )
    }

    private fun getUrlByIcon(idIcon: String): String =
        "http://openweathermap.org/img/wn/${idIcon}@2x.png"

    private fun getUrlByIconHeight(idIcon: String): String =
        "http://openweathermap.org/img/wn/${idIcon}@4x.png"

    private fun getCurrentDateByUTC(timeZone: Int): Date {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val millis = Calendar.getInstance().timeInMillis + (timeZone * 1000)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar.time
    }

    private fun getDateByUTC(dateUtcInSecond: Long, timeZone: Int): Date{
        val millis = (dateUtcInSecond  + timeZone) * 1000
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar.time
    }

    private fun getWindDirection(radius: Int): String{
        val arr = arrayOf("N","NNE","NE","ENE","E","ESE", "SE", "SSE","S","SSW","SW","WSW","W","WNW","NW","NNW")
        return arr[radius % 16]
    }
}