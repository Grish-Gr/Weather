package com.example.weather.view.adapters.holders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.CardSaveLocationBinding
import com.example.weather.model.data.SavedForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.view.adapters.ActionClickOnCardLocation
import java.text.SimpleDateFormat

class SavedForecastHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding: CardSaveLocationBinding = CardSaveLocationBinding.bind(view)

    @SuppressLint("SimpleDateFormat")
    fun initView(
        forecastData: SavedForecastData,
        actionClick: ActionClickOnCardLocation
    ) {
        binding.lastDateSaveWeather.text =
            String.format(binding.root.resources.getString(R.string.ex_last_date_save_weather), SimpleDateFormat("dd.MM.yyyy").format(forecastData.date))
        binding.textLocationCityCountry.text =
            String.format("%s, %s", forecastData.location.locationName, forecastData.location.country)
        binding.lastSaveTemperature.text =
            TemperatureDetail.getTemperatureCelsius(forecastData.temperature.temperature)
        binding.cardViewLocation.setOnClickListener {
            actionClick(forecastData.location)
        }
    }
}