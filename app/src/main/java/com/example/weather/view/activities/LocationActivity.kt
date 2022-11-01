package com.example.weather.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.example.weather.R
import com.example.weather.databinding.ActivityLocationBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.fragments.SearchLocationFragment
import com.example.weather.viewmodels.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationActivity: BaseActivity() {

    private lateinit var binding: ActivityLocationBinding
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserveInViewModel()
        val fragment = SearchLocationFragment()
        /*fragment.chooseLocation.observe(this){
            sendResult(it)
        }*/

        Log.e("TAG", "in OnCreate")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_location_fragment, fragment)
            .commit()
    }

    private fun initObserveInViewModel(){
        viewModel.chooseLocation.observe(this){
            sendResult(it)
        }
    }

    private fun sendResult(location: LocationData){
        val intent = Intent()
        Log.e("TAG", "In Send Result $location")
        intent.putExtra(KEY_RESULT_LOCATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }
}