package com.example.weather.view.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.StepCardWeatherBinding
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class StepWeatherWithDetailHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding: StepCardWeatherBinding = StepCardWeatherBinding.bind(view)

    fun initView(forecast: StepForecastData){
        Picasso.get()
            .load(forecast.weather.urlIconWeatherHeight)
            .into(binding.iconStepForecast)
        binding.dateStepForecast.text =
            SimpleDateFormat("HH:mm, d MMM", Locale.getDefault()).format(forecast.date)
        binding.descriptionStepForecast.text = forecast.weather.descriptionWeather
        binding.stepForecastTemperature.text = TemperatureDetail.getTemperatureCelsius(forecast.temperature.temperature)
    }
}