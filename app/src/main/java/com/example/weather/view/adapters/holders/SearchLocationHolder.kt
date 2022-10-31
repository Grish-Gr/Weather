package com.example.weather.view.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.CardLocationBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.adapters.ActionClickOnCardLocation

class SearchLocationHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CardLocationBinding.bind(view)

    fun initView(locationData: LocationData, actionClick: ActionClickOnCardLocation){
        binding.textLocationCityCountry.text = String.format("%s, %s", locationData.locationName, locationData.country)
        binding.cardViewLocation.setOnClickListener {
            actionClick(locationData)
        }
    }
}