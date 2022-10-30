package com.example.weather.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.databinding.ActivityLocationBinding
import com.example.weather.view.fragments.SearchSaveLocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", layoutInflater.toString())
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("TAG", "in OnCreate")
        // Initialize the AutocompleteSupportFragment.

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_location_fragment, SearchSaveLocationFragment())
            .commit()
    }
}