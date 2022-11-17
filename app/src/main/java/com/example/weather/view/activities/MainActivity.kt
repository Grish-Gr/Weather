package com.example.weather.view.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.fragments.InfoWeatherFragment
import com.example.weather.view.fragments.MainWeatherFragment
import com.example.weather.view.fragments.StepWeatherListFragment
import com.example.weather.view.fragments.setBackgroundShapeByDate
import com.example.weather.viewmodels.CurrentWeatherViewModel
import com.example.weather.viewmodels.StepWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModels()
    private val stepWeatherViewModel: StepWeatherViewModel by viewModels()
    private val launcherActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val location: LocationData? = result.data?.getParcelableExtra(KEY_RESULT_LOCATION)
            if (location != null){
                binding.currentLocation.text = location.locationName
                changeLocation(location)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.currentLocation.text = currentWeatherViewModel.getLastLocation().locationName
        initAction()
        initRefreshData()
        initFragments()
    }

    private fun initAction(){
        binding.currentLocation.setOnClickListener {
            openCitySearchActivity()
        }
        currentWeatherViewModel.currentForecast.observe(this){
            binding.appBarCurrentForecast.setBackgroundShapeByDate(it.date)
            binding.refreshData.isRefreshing = false
        }
    }

    private fun initRefreshData(){
        binding.refreshData.setProgressViewOffset(true, 0, 200)
        binding.refreshData.setOnRefreshListener { updateData() }
        binding.appBarCurrentForecast.addOnOffsetChangedListener { _, verticalOffset ->
            binding.refreshData.isEnabled = verticalOffset >= 0
        }
    }

    private fun initFragments(){
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_current_weather, MainWeatherFragment())
                .add(R.id.container_info_weather, InfoWeatherFragment())
                .add(R.id.container_list_step_weather, StepWeatherListFragment())
                .commit()
        }
        currentWeatherViewModel.getCurrentForecast()
        stepWeatherViewModel.getShortListStepWeather(count = StepWeatherListFragment.countShortInterval)
    }

    private fun openCitySearchActivity(){
        val intent = Intent(this, LocationActivity::class.java)
        launcherActivity.launch(intent)
    }

    private fun updateData(){
        currentWeatherViewModel.getCurrentForecast()
        stepWeatherViewModel.getShortListStepWeather(count = StepWeatherListFragment.countShortInterval)
    }

    private fun changeLocation(location: LocationData){
        currentWeatherViewModel.getCurrentForecast(
            latitude = location.latitude,
            longitude = location.longitude
        )
        stepWeatherViewModel.getShortListStepWeather(
            latitude = location.latitude,
            longitude = location.longitude,
            count = StepWeatherListFragment.countShortInterval
        )
    }
}