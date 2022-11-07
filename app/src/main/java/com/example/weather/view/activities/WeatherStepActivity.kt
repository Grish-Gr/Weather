package com.example.weather.view.activities

import android.os.Bundle
import android.os.PersistableBundle
import com.example.weather.R
import com.example.weather.databinding.ActivityStepWeatherBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.fragments.WeatherStepFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherStepActivity: BaseActivity() {

    private lateinit var binding: ActivityStepWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openWeatherStepFragment()
    }

    private fun openWeatherStepFragment(){
        val location = intent.getParcelableExtra(KEY_INTENT_LOCATION) ?: LocationData.DefaultLocation
        val bundle = Bundle()
        bundle.putParcelable(WeatherStepFragment.KEY_BUNDLE_LOCATION, location)
        val fragment = WeatherStepFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_step_weather_fragment, fragment)
            .commit()
    }
}