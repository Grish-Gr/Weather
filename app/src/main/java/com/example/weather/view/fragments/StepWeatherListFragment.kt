package com.example.weather.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentListWeatherBinding
import com.example.weather.databinding.FragmentListWeatherStepBinding
import com.example.weather.databinding.FragmentWeatherInfoBinding
import com.example.weather.view.adapters.StepWeatherAdapter
import com.example.weather.viewmodels.CurrentLocationViewModel
import com.example.weather.viewmodels.LocationViewModel
import com.example.weather.viewmodels.StepWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepWeatherListFragment: Fragment() {

    private lateinit var binding: FragmentListWeatherStepBinding
    private val mainViewModel: CurrentLocationViewModel by activityViewModels()
    private val viewModel: StepWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListWeatherStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        viewModel.getListStepWeather(count =  8)
    }

    private fun initRecyclerView(){
        binding.listStepWeather.adapter = StepWeatherAdapter()
        binding.listStepWeather.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun initViewModel(){
        viewModel.listStepWeather.observe(this.viewLifecycleOwner){
            (binding.listStepWeather.adapter as StepWeatherAdapter).setListStepWeather(it)
        }
        mainViewModel.currentLocation.observe(this.viewLifecycleOwner){
            viewModel.getListStepWeather(it.locationName, 8)
        }
    }
}