package com.example.weather.model

import android.content.Context
import com.example.weather.model.data.LocationData
import com.example.weather.model.interfaces.SharedPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context
): SharedPreferencesRepository {

    override fun saveLastLocation(location: LocationData) {
        val sharedPreferences = applicationContext.getSharedPreferences(LAST_LOCATION, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString(KEY_NAME_LOCATION, location.locationName)
        edit.putFloat(KEY_LATITUDE_LOCATION, location.latitude)
        edit.putFloat(KEY_LONGITUDE_LOCATION, location.longitude)
        edit.putString(KEY_COUNTRY_LOCATION, location.country)
        edit.apply()

    }

    override fun getLastLocation(): LocationData {
        val sharedPreferences = applicationContext.getSharedPreferences(LAST_LOCATION, Context.MODE_PRIVATE)
        return LocationData(
            locationName = sharedPreferences.getString(KEY_NAME_LOCATION, LocationData.DefaultLocation.locationName)
                ?: LocationData.DefaultLocation.locationName,
            latitude = sharedPreferences.getFloat(KEY_LATITUDE_LOCATION, LocationData.DefaultLocation.latitude),
            longitude = sharedPreferences.getFloat(KEY_LONGITUDE_LOCATION, LocationData.DefaultLocation.longitude),
            country = sharedPreferences.getString(KEY_COUNTRY_LOCATION, LocationData.DefaultLocation.country)
                ?: LocationData.DefaultLocation.country
        )
    }

    companion object{
        private const val LAST_LOCATION = "LastLocationName"
        private const val KEY_NAME_LOCATION = "KeyNameLocation"
        private const val KEY_LATITUDE_LOCATION = "KeyLatitudeLocation"
        private const val KEY_LONGITUDE_LOCATION = "KeyLongitudeLocation"
        private const val KEY_COUNTRY_LOCATION = "KeyCountryLocation"
    }
}