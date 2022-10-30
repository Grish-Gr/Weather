package com.example.weather.view.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.StepCardWeatherBinding
import com.example.weather.model.data.StepForecastData
import com.squareup.picasso.Picasso

class StepWeatherHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = StepCardWeatherBinding.bind(view)

    fun fillView(stepWeather: StepForecastData){
        Picasso.get()
            .load(stepWeather.weather.urlIconWeatherHeight)
            .into(binding.imageStepWeather)
    }
}