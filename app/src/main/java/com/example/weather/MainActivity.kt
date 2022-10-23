package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.weather.data.network.GeocodingService
import com.example.weather.data.network.WeatherService
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.Result
import com.example.weather.model.interfaces.WeatherRepository
import com.example.weather.usecases.GetCurrentWeatherUseCase
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get()
            .load("http://openweathermap.org/img/wn/10d@2x.png")
            .into(binding.clickService)

        binding.clickService.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val response = getCurrentWeatherUseCase.getCurrentWeather("Екатеринбург")
                withContext(Dispatchers.Main){
                    Picasso.get()
                        .load((response as Result.Success).value.weather.urlIconWeather)
                        .into(binding.clickService)
                }
                /*forecast.listForecast.map {
                    Log.e("TAG", "${it.textDate}: minTemp - ${it.infoTemperatures.minTemperature}; maxTemp - ${it.infoTemperatures.maxTemperature}")
                    it.weather.map { info -> Log.e("TAG", info.description) }
                }*/
            }
        }
    }
}