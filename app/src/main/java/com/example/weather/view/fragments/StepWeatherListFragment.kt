package com.example.weather.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.FragmentListWeatherStepBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.adapters.StepWeatherAdapter
import com.example.weather.viewmodels.CurrentLocationViewModel
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
        initAction()
        initViewModel()
    }

    private fun initRecyclerView(){
        binding.listStepWeather.adapter = StepWeatherAdapter()
        binding.listStepWeather.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun initViewModel(){
        viewModel.listStepWeather.observe(this.viewLifecycleOwner){
            binding.showMoreStepWeather.visibility = if(it.isEmpty()) View.GONE else View.VISIBLE
            binding.showMoreStepWeather.isClickable = true
            if (it.size > countShortInterval){
                (binding.listStepWeather.adapter as StepWeatherAdapter)
                    .addListStepWeather(it.subList(countShortInterval, it.size))
            } else {
                (binding.listStepWeather.adapter as StepWeatherAdapter).setListStepWeather(it)
            }
        }
        mainViewModel.currentLocation.observe(this.viewLifecycleOwner){
            Log.e("TAG", "Inside observe StepWeather")
            viewModel.getShortListStepWeather(
                latitude = it.latitude,
                longitude = it.longitude,
                count = countShortInterval,
                errorAction = {
                    showToast(R.string.no_internet_access)
                    (binding.listStepWeather.adapter as StepWeatherAdapter).setListStepWeather(emptyList())
                    binding.showMoreStepWeather.visibility = View.GONE
                })
        }
    }

    private fun initAction(){
        binding.showMoreStepWeather.setOnClickListener {
            if ((binding.listStepWeather.adapter as StepWeatherAdapter).itemCount <= countShortInterval){
                changeTextActionButton(R.string.hide_list)
                binding.showMoreStepWeather.isClickable = false
                viewModel.getAllListStepWeather(
                    latitude = mainViewModel.currentLocation.value?.latitude
                        ?: LocationData.DefaultLocation.latitude,
                    longitude = mainViewModel.currentLocation.value?.longitude
                        ?: LocationData.DefaultLocation.longitude,
                    errorAction = {
                        showToast(R.string.no_internet_access)
                        binding.showMoreStepWeather.isClickable = true
                        changeTextActionButton(R.string.show_more)
                    })
            } else {
                (binding.listStepWeather.adapter as StepWeatherAdapter).removeStepWeather(0, countShortInterval)
                changeTextActionButton(R.string.show_more)
            }
        }
    }

    private fun showToast(@StringRes idResMessage: Int){
        Toast.makeText(this.context, resources.getText(idResMessage), Toast.LENGTH_SHORT).show()
    }

    private fun changeTextActionButton(@StringRes idResText: Int){
        binding.showMoreStepWeather.text = resources.getText(idResText)
    }

    companion object{
        private const val countShortInterval = 8
    }
}