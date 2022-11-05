package com.example.weather.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weather.databinding.FragmentWeatherMainBinding
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.viewmodels.CurrentLocationViewModel
import com.example.weather.viewmodels.CurrentWeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainWeatherFragment: Fragment() {

    private lateinit var binding: FragmentWeatherMainBinding
    private val mainViewModel: CurrentLocationViewModel by activityViewModels()
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
    }

    private fun initViewModel(){
        viewModel.currentForecast.observe(this.viewLifecycleOwner){ forecast ->
            fillView(forecast)
        }
        mainViewModel.currentLocation.observe(this.viewLifecycleOwner){
            viewModel.getCurrentForecast(latitude = it.latitude, longitude = it.longitude)
        }
    }

    private fun fillView(forecast: ForecastData){
        if (forecast is CurrentForecastData){
            Picasso.get()
                .load(forecast.icons.urlIconWeatherHeight)
                .into(binding.imageCurrentWeather)
        }
        val date = forecast.date
        binding.currentTemperature.text = TemperatureDetail.getTemperatureCelsius(forecast.temperature.temperature.toInt())
        binding.currentDate.text = SimpleDateFormat("dd MMM, EEE", Locale.getDefault()).format(date)
        binding.currentTime.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
        binding.descriptionCurrentWeather.text = forecast.weather.descriptionWeather.replaceFirstChar { it.uppercase() }
    }
}