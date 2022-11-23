package com.example.weather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.data.ForecastData
import com.example.weather.model.data.LocationData
import com.example.weather.model.data.SavedForecastData
import com.example.weather.view.adapters.holders.SavedForecastHolder

typealias ActionSwipe = (LocationData) -> Unit

class SearchSavedForecastAdapter(
    private val actionClick: ActionClickOnCardLocation,
    private val actionSwipe: ActionSwipe,
): RecyclerView.Adapter<SavedForecastHolder>() {

    private var listSavedForecast: MutableList<SavedForecastData> =
        emptyList<SavedForecastData>().toMutableList()

    fun deleteItemLocation(position: Int){
        actionSwipe(listSavedForecast[position].location)
        listSavedForecast.removeAt(position)
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listSavedForecast: List<SavedForecastData>){
        this.listSavedForecast = listSavedForecast.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteForecast(location: LocationData){
        val indexDeleteForecast = listSavedForecast.indexOfFirst { it.location == location }
        listSavedForecast.removeAt(indexDeleteForecast)
        notifyItemRemoved(indexDeleteForecast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedForecastHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_save_location, parent, false)
        return SavedForecastHolder(view)
    }

    override fun onBindViewHolder(holder: SavedForecastHolder, position: Int) {
        holder.initView(listSavedForecast[position], actionClick)
    }

    override fun getItemCount(): Int  = listSavedForecast.size
}