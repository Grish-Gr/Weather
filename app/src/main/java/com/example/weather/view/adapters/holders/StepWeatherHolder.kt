package com.example.weather.view.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.StepCardWeatherBinding
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.view.fragments.setBackgroundShapeByDate
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class StepWeatherHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = StepCardWeatherBinding.bind(view)

    fun fillView(stepWeather: StepForecastData){
        Picasso.get()
            .load(stepWeather.weather.urlIconWeatherHeight)
            .into(binding.imageStepWeather)
        binding.layoutStepWeather.setBackgroundShapeByDate(stepWeather.date)
        binding.timeStepWeather.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(stepWeather.date)
        binding.temperatureStepWeather.text = TemperatureDetail
            .getTemperatureCelsius(stepWeather.temperature.temperature)
        binding.maxStepTemperature.text = TemperatureDetail
            .getTemperatureCelsius(stepWeather.temperature.maxTemperature ?: stepWeather.temperature.temperature)
        binding.minStepTemperature.text = TemperatureDetail
            .getTemperatureCelsius(stepWeather.temperature.minTemperature ?: stepWeather.temperature.temperature)
    }
}