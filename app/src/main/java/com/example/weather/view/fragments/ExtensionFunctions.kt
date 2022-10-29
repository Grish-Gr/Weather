package com.example.weather.view.fragments

import androidx.fragment.app.Fragment
import com.example.weather.R
import java.util.*

fun Fragment.getCurrentDateByUTC(shiftInSecFromUTC: Int): Date{
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val millis = Calendar.getInstance().timeInMillis + (shiftInSecFromUTC * 1000)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    return calendar.time
}