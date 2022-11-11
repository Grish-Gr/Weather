package com.example.weather.view.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemStepWeatherBinding
import com.example.weather.model.data.StepForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import java.text.SimpleDateFormat
import java.util.*

class StepWeatherHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemStepWeatherBinding.bind(view)

    fun fillView(stepWeather: StepForecastData){
        binding.dateStepWeather.text = SimpleDateFormat("HH:mm, d MMM", Locale.getDefault()).format(stepWeather.date)
        binding.tempStepWeather.text = TemperatureDetail.getTemperatureCelsius(stepWeather.temperature.temperature)
    }
}