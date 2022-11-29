package com.example.weather.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.data.StepForecastData
import com.example.weather.ui.adapters.holders.StepWeatherHolder

class StepWeatherAdapter: RecyclerView.Adapter<StepWeatherHolder>() {

    private var listStepWeather: MutableList<StepForecastData> = emptyList<StepForecastData>().toMutableList()

    @SuppressLint("NotifyDataSetChanged")
    fun setListStepWeather(listStepWeather: List<StepForecastData>){
        this.listStepWeather.clear()
        this.listStepWeather.addAll(listStepWeather)
        notifyDataSetChanged()
    }

    fun addListStepWeather(listStepWeather: List<StepForecastData>){
        val index = listStepWeather.lastIndex
        this.listStepWeather.addAll(listStepWeather)
        notifyItemRangeChanged(index, listStepWeather.size)
    }

    fun removeStepWeather(start: Int = 0, end: Int){
        val lastInd = listStepWeather.size
        listStepWeather = listStepWeather.subList(start, end)
        notifyItemRangeRemoved(end, lastInd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepWeatherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_weather, parent, false)
        return StepWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: StepWeatherHolder, position: Int) {
        holder.fillView(listStepWeather[position])
    }

    override fun getItemCount(): Int = listStepWeather.size
}