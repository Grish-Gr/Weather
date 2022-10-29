package com.example.weather.view.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.weather.databinding.FragmentWeatherMainBinding
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.TemperatureData
import com.example.weather.viewmodels.CurrentWeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainWeatherFragment: Fragment() {

    private lateinit var binding: FragmentWeatherMainBinding
    private val viewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewModel.getCurrentForecast("Екатеринбург")
    }

    private fun initViewModel(){
        viewModel.currentForecast.observe(this.viewLifecycleOwner){ forecast ->
            fillView(forecast)
        }
    }

    private fun fillView(forecast: CurrentForecastData){
        Picasso.get()
            .load(forecast.weather.urlIconWeatherHeight)
            .into(binding.imageCurrentWeather)
        val date = forecast.getCurrentDateByUTC()
        binding.currentTemperature.text = TemperatureData.getTemperatureCelsius(forecast.temperature.temperature)
        binding.currentDate.text = SimpleDateFormat("dd MMM, EEE", Locale.getDefault()).format(date)
        binding.currentTime.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
        binding.descriptionCurrentWeather.text = forecast.weather.descriptionWeather.replaceFirstChar { it.uppercase() }
    }
}