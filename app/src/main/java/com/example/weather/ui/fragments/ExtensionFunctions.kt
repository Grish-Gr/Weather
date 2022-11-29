package com.example.weather.ui.fragments

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.example.weather.R
import java.util.*

@SuppressLint("UseCompatLoadingForDrawables")
fun ViewGroup.setBackgroundShapeByDate(date: Date) {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val hour = (calendar.get(Calendar.HOUR_OF_DAY))
    val res = when{
        hour in 0..3 -> R.drawable.shape_deep_night
        hour <= 5    -> R.drawable.shape_light_night
        hour <= 7    -> R.drawable.shape_early_morning
        hour <= 9    -> R.drawable.shape_sunrise
        hour <= 12   -> R.drawable.shape_morning
        hour <= 14   -> R.drawable.shape_midday
        hour <= 16   -> R.drawable.shape_late_day
        hour <= 19   -> R.drawable.shape_early_evening
        hour <= 21   -> R.drawable.shape_evening
        hour <= 23   -> R.drawable.shape_late_evening
        else         -> R.drawable.shape_night
    }
    background = resources.getDrawable(res, this.context.theme)
}