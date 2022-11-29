package com.example.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weather.databinding.FragmentSunInfoBinding
import com.example.weather.model.data.CurrentForecastData
import com.example.weather.viewmodels.CurrentWeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

class InfoSunFragment: Fragment() {

    private lateinit var binding: FragmentSunInfoBinding
    private val currentWeatherViewModel: CurrentWeatherViewModel by activityViewModels()
    private var hideSunTime: Boolean = false
        set(value) {
            binding.sunArch.visibility = if (value) View.GONE else View.VISIBLE
            binding.sunriseTime.visibility = if (value) View.GONE else View.VISIBLE
            binding.sunsetTime.visibility = if (value) View.GONE else View.VISIBLE
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSunInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideSunTime = false
        currentWeatherViewModel.currentForecast.observe(this.viewLifecycleOwner){
            if (it is CurrentForecastData){
                binding.causeInternetAccess.visibility = View.INVISIBLE
                hideSunTime = false
                fillSunInfo(it)
            } else {
                binding.causeInternetAccess.visibility = View.VISIBLE
                hideSunTime = true
            }
        }
    }

    private fun fillSunInfo(currentForecast: CurrentForecastData){
        binding.sunArch.setProgressSun(
            sunrise = currentForecast.sunTime.dateSunrise,
            sunset = currentForecast.sunTime.dateSunset,
            currentTime = currentForecast.date
        )
        binding.sunriseTime.text =
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(currentForecast.sunTime.dateSunrise)
        binding.sunsetTime.text =
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(currentForecast.sunTime.dateSunset)
    }
}