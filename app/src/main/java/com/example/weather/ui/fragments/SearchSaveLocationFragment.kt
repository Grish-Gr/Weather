package com.example.weather.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.FragmentLocationSaveSearchBinding
import com.example.weather.model.data.LocationData
import com.example.weather.ui.adapters.SearchSavedForecastAdapter
import com.example.weather.ui.adapters.TouchHelperSearchAdapter
import com.example.weather.viewmodels.LocationViewModel
import com.example.weather.viewmodels.SearchSaveLocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSaveLocationFragment: Fragment() {

    private lateinit var binding: FragmentLocationSaveSearchBinding
    private val viewModelLocation: LocationViewModel by activityViewModels()
    private val viewModel: SearchSaveLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationSaveSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEditText()
        initAction()
        initRecyclerView()
        initViewModel()
    }

    private fun initEditText(){
        binding.searchField.editText?.addTextChangedListener {
            if (it.toString().isBlank()){
                viewModel.getLocations()
            } else {
                viewModel.getLocations(nameCity = it.toString())
            }
            binding.indicatorSaveSearch.visibility = View.VISIBLE
            binding.indicatorSaveSearch.isIndeterminate = true
        }
    }

    private fun initAction(){
        binding.addLocation.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_location_fragment, SearchLocationFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initRecyclerView(){
        val adapter = SearchSavedForecastAdapter(
            actionClick = { viewModelLocation.setLocation(it) },
            actionSwipe = { viewModel.deleteLocation(it) }
        )
        val touchHelper = ItemTouchHelper(TouchHelperSearchAdapter(swipeAction = { position ->
            adapter.deleteItemLocation(position)
        }))
        binding.listSaveLocation.adapter = adapter
        touchHelper.attachToRecyclerView(binding.listSaveLocation)
        binding.listSaveLocation.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        viewModel.getLocations()
    }

    private fun initViewModel(){
        viewModel.listSavedForecast.observe(this.viewLifecycleOwner){
            binding.nothingFoundText.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            (binding.listSaveLocation.adapter as SearchSavedForecastAdapter).setList(it)
            binding.indicatorSaveSearch.visibility = View.INVISIBLE
            binding.indicatorSaveSearch.isIndeterminate = false
        }
    }
}