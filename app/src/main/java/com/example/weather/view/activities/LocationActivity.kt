package com.example.weather.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import com.example.weather.R
import com.example.weather.databinding.ActivityLocationBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.fragments.SearchLocationFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationActivity: BaseActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("TAG", "in OnCreate")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_location_fragment, SearchLocationFragment())
            .commit()
    }

    private fun sendResult(location: LocationData){
        val intent = Intent()
        intent.putExtra(KEY_RESULT_LOCATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }
}