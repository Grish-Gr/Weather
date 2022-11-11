package com.example.weather.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.weather.R
import com.example.weather.databinding.ActivityLocationBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.fragments.SearchSaveLocationFragment
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
        initAction()
        if (supportFragmentManager.backStackEntryCount == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_location_fragment, SearchSaveLocationFragment())
                .commit()
        }
    }

    private fun initObserveInViewModel(){
        viewModel.chooseLocation.observe(this){
            sendResult(it)
        }
    }

    private fun initAction(){
        binding.backToMainActivity.setOnClickListener {
            finish()
        }
    }

    private fun sendResult(location: LocationData){
        val intent = Intent()
        Log.e("TAG", "In Send Result $location")
        intent.putExtra(KEY_RESULT_LOCATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object{
        private const val REQUEST_CODE_LOCATION = 1
    }
}