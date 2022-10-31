package com.example.weather.view.activities

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

open class BaseActivity: AppCompatActivity() {

    companion object{
        const val KEY_RESULT_LOCATION = "KeyResultLocation"
    }
}