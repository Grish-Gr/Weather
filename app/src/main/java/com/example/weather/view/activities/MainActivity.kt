package com.example.weather.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.fragments.InfoWeatherFragment
import com.example.weather.view.fragments.MainWeatherFragment
import com.example.weather.view.fragments.StepWeatherListFragment
import com.example.weather.view.fragments.setBackgroundShapeByDate
import com.example.weather.viewmodels.CurrentLocationViewModel
import com.example.weather.viewmodels.CurrentWeatherViewModel
import com.example.weather.viewmodels.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val locationViewModel: CurrentLocationViewModel by viewModels()
    private val viewModel: CurrentWeatherViewModel by viewModels()
    private val launcherActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val location: LocationData? = result.data?.getParcelableExtra(KEY_RESULT_LOCATION)
            Log.e("TAG", location?.locationName ?: "")
            if (location != null){
                Log.e("TAG", location.locationName)
                locationViewModel.updateCurrentLocation(location)
                binding.currentLocation.text = location.locationName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAction()
        binding.currentLocation.text = locationViewModel.currentLocation.value?.locationName
            ?: LocationData.DefaultLocation.locationName
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_current_weather, MainWeatherFragment())
                .add(R.id.container_info_weather, InfoWeatherFragment())
                .add(R.id.container_list_step_weather, StepWeatherListFragment())
                .commit()
        }
    }

    private fun openCitySearchActivity(){
        val intent = Intent(this, LocationActivity::class.java)
        launcherActivity.launch(intent)
    }

    private fun initAction(){
        binding.currentLocation.setOnClickListener {
            openCitySearchActivity()
        }
        viewModel.currentForecast.observe(this){
            binding.layoutCurrentWeather.setBackgroundShapeByDate(it.date)
        }
        /*mainViewModel.currentLocation.observe(this){
            binding.swipeRefreshData?.isRefreshing = false
        }
        binding.swipeRefreshData?.setOnRefreshListener {
            mainViewModel.refreshCurrentLocation()
        }*/
    }
}