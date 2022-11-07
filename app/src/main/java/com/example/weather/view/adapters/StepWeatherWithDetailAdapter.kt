package com.example.weather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.data.StepForecastData
import com.example.weather.view.adapters.holders.StepWeatherWithDetailHolder

class StepWeatherWithDetailAdapter: RecyclerView.Adapter<StepWeatherWithDetailHolder>() {

    private var listForecast: List<StepForecastData> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setListWeather(listForecast: List<StepForecastData>){
        this.listForecast = listForecast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepWeatherWithDetailHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.step_card_weather, parent, false)
        return StepWeatherWithDetailHolder(view)
    }

    override fun onBindViewHolder(holder: StepWeatherWithDetailHolder, position: Int) {
        holder.initView(listForecast[position])
    }

    override fun getItemCount(): Int = listForecast.size
}