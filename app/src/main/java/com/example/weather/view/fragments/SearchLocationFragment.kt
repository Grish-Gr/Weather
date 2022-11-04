package com.example.weather.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.FragmentLocationSearchBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.adapters.SearchLocationAdapter
import com.example.weather.viewmodels.LocationViewModel
import com.example.weather.viewmodels.SearchLocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SearchLocationFragment: Fragment() {

    private val viewModel: SearchLocationViewModel by viewModels()
    private val viewModelLocation: LocationViewModel by activityViewModels()
    private val _chooseLocation = MutableLiveData<LocationData>()
    private lateinit var binding: FragmentLocationSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserveInViewModel()
        initInputSearchLocation()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.listSearchLocation.adapter = SearchLocationAdapter(
            actionClick = {
                viewModelLocation.setLocation(it)
                _chooseLocation.value = it
            }
        )
        binding.listSearchLocation.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }

    private fun initInputSearchLocation(){
        binding.searchField.editText?.addTextChangedListener {
            if (it.toString().isBlank()) return@addTextChangedListener
            viewModel.getLocation(
                nameCity = it.toString(),
                errorAction = {
                    Toast.makeText(this.context, getString(R.string.no_internet_access), Toast.LENGTH_SHORT).show()
                }
            )
            binding.indicatorSearch.visibility = View.VISIBLE
            binding.indicatorSearch.isIndeterminate = true
        }
    }


    private fun initObserveInViewModel(){
        viewModel.listSearchingLocation.observe(this.viewLifecycleOwner){
            if (it.isEmpty()){
                binding.nothingFoundText.visibility = View.VISIBLE
            } else {
                binding.nothingFoundText.visibility = View.GONE
            }
            (binding.listSearchLocation.adapter as SearchLocationAdapter).setListLocation(it)
            binding.indicatorSearch.isIndeterminate = false
            binding.indicatorSearch.visibility = View.INVISIBLE
        }
    }
}