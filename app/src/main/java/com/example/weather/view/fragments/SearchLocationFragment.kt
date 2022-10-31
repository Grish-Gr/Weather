package com.example.weather.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentLocationSearchBinding
import com.example.weather.model.data.LocationData
import com.example.weather.view.adapters.SearchLocationAdapter
import com.example.weather.viewmodels.SearchLocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchLocationFragment: Fragment() {

    private val viewModel: SearchLocationViewModel by viewModels()
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
            }
        )
        binding.listSearchLocation.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }

    private fun initInputSearchLocation(){
        binding.searchField.editText?.addTextChangedListener {
            viewModel.getLocation(it.toString())
        }
    }

    private fun initObserveInViewModel(){
        viewModel.listSearchingLocation.observe(this.viewLifecycleOwner){
            Log.e("TAG", it.toString())
            (binding.listSearchLocation.adapter as SearchLocationAdapter).setListLocation(it)
        }
    }
}