package com.example.weather.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentStepWeatherBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.adapters.StepWeatherWithDetailAdapter
import com.example.weather.viewmodels.StepWeatherViewModel
import com.example.weather.viewmodels.WeatherStepViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherStepFragment: Fragment() {

    private lateinit var binding: FragmentStepWeatherBinding
    private val stepWeatherViewModel: WeatherStepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeDataInViewModel()
        val location = arguments?.getParcelable(KEY_BUNDLE_LOCATION) ?: LocationData.DefaultLocation
        binding.progressLoad.visibility = ProgressBar.VISIBLE
        stepWeatherViewModel.getAllForecastByInterval(location)
    }

    private fun initRecyclerView(){
        binding.listStepWeather.adapter = StepWeatherWithDetailAdapter()
        binding.listStepWeather.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }

    private fun observeDataInViewModel(){
        stepWeatherViewModel.listStepForecast.observe(this.viewLifecycleOwner){
            (binding.listStepWeather.adapter as StepWeatherWithDetailAdapter).setListWeather(it)
            binding.progressLoad.visibility = ProgressBar.INVISIBLE
        }
    }

    companion object{
        const val KEY_BUNDLE_LOCATION = "KeyBundleLocation"
    }
}