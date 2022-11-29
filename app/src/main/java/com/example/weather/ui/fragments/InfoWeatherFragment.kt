package com.example.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weather.databinding.FragmentWeatherInfoBinding
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.detail.TemperatureDetail
import com.example.weather.viewmodels.CurrentWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoWeatherFragment: Fragment() {

    private lateinit var binding: FragmentWeatherInfoBinding
    private val currentWeatherViewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        currentWeatherViewModel.currentForecast.observe(this.viewLifecycleOwner){ forecast ->
            fillView(forecast)
        }
    }

    private fun fillView(forecast: ForecastData){
        binding.feelsLikeTemperature.text = TemperatureDetail
            .getTemperatureCelsius(forecast.temperature.feelsLikeTemperature)
        binding.cloudiness.text = String.format("%s", forecast.weather.cloudiness) + '%'
        binding.atmPressure.text = String.format("%s hPA", forecast.weather.pressure)
        binding.humidity.text = String.format("%s", forecast.weather.humidity) + '%'
        binding.winder.text = forecast.weather.windDirection.toString()
        binding.visibilityWeather.text = String.format("%.1f km", (forecast.weather.visibility / 1000.0))
    }
}