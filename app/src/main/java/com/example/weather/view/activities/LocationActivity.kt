package com.example.weather.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.databinding.ActivityLocationBinding

class LocationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", layoutInflater.toString())
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("TAG", "in OnCreate")
        // Initialize the AutocompleteSupportFragment.

    }
}