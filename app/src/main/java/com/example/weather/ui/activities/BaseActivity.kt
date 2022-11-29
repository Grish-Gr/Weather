package com.example.weather.ui.activities

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    companion object{
        const val KEY_RESULT_LOCATION = "KeyResultLocation"
        const val KEY_INTENT_LOCATION = "KeyIntentLocation"
    }
}