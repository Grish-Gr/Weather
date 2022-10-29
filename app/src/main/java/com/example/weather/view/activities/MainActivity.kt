package com.example.weather.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.usecases.GetCurrentWeatherUseCase
import com.example.weather.view.fragments.InfoWeatherFragment
import com.example.weather.view.fragments.MainWeatherFragment
import com.example.weather.view.fragments.StepWeatherListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.location.setOnClickListener {
            openCitySearchFragment()
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container_current_weather, MainWeatherFragment())
            .add(R.id.container_info_weather, InfoWeatherFragment())
            .add(R.id.container_list_step_weather, StepWeatherListFragment())
            .commit()
    }

    private fun openCitySearchFragment(){
        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
    }
}