package com.example.weather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.data.LocationData
import com.example.weather.view.adapters.holders.SearchLocationHolder

typealias ActionClickOnCardLocation = (location: LocationData) -> Unit
typealias ActionLongClickCardLocation = (location: LocationData, view: View?) -> Boolean

class SearchLocationAdapter(
    private val actionClick: ActionClickOnCardLocation
): RecyclerView.Adapter<SearchLocationHolder>() {

    private var listLocation: List<LocationData> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setListLocation(listLocation: List<LocationData>){
        this.listLocation = listLocation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLocationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_location, parent, false)
        return SearchLocationHolder(view)
    }

    override fun onBindViewHolder(holder: SearchLocationHolder, position: Int) {
        holder.initView(listLocation[position], actionClick)
    }

    override fun getItemCount(): Int = listLocation.size
}