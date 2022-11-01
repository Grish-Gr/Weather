package com.example.weather.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.data.LocationData
import com.example.weather.usecases.GetCurrentWeatherUseCase
import com.example.weather.view.fragments.InfoWeatherFragment
import com.example.weather.view.fragments.MainWeatherFragment
import com.example.weather.view.fragments.StepWeatherListFragment
import com.example.weather.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val launcherActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val location: LocationData? = result.data?.getParcelableExtra(KEY_RESULT_LOCATION)
            if (location != null){
                mainViewModel.updateCurrentLocation(location)
                binding.location.text = location.locationName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAction()

        if (supportFragmentManager.backStackEntryCount == 0) {
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
        binding.location.setOnClickListener {
            openCitySearchActivity()
        }
        mainViewModel.currentLocation.observe(this){
            binding.swipeRefreshData?.isRefreshing = false
        }
        binding.swipeRefreshData?.setOnRefreshListener {
            mainViewModel.refreshCurrentLocation()
        }
    }
}