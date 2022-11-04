package com.example.weather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.data.StepForecastData
import com.example.weather.view.adapters.holders.StepWeatherHolder

class StepWeatherAdapter: RecyclerView.Adapter<StepWeatherHolder>() {

    private var listStepWeather: List<StepForecastData> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setListStepWeather(listStepWeather: List<StepForecastData>){
        this.listStepWeather = listStepWeather
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepWeatherHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.item_step_weather, parent, false)
        return StepWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: StepWeatherHolder, position: Int) {
        holder.fillView(listStepWeather[position])
    }

    override fun getItemCount(): Int = listStepWeather.size
}