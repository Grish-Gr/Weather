package com.example.weather.view.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

open class BaseActivity: AppCompatActivity() {

    companion object{
        const val KEY_RESULT_LOCATION = "KeyResultLocation"
    }
}