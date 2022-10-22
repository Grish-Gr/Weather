package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.weather.data.network.GeocodingService
import com.example.weather.data.network.WeatherService
import com.example.weather.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var geocodingService: GeocodingService
    @Inject lateinit var weatherService: WeatherService
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get()
            .load("http://openweathermap.org/img/wn/10d@2x.png")
            .into(binding.clickService)

        binding.clickService.setOnClickListener {
            lifecycleScope.launch {
                val forecast = weatherService.getCurrentForecast(nameCity = "Екатеринбург", language = "ru")
                /*forecast.listForecast.map {
                    Log.e("TAG", "${it.textDate}: minTemp - ${it.infoTemperatures.minTemperature}; maxTemp - ${it.infoTemperatures.maxTemperature}")
                    it.weather.map { info -> Log.e("TAG", info.description) }
                }*/
            }
        }
    }
}