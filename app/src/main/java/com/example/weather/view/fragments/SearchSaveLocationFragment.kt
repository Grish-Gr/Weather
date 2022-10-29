package com.example.weather.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weather.databinding.FragmentLocationSaveSearchBinding
import com.example.weather.viewmodels.SearchSaveLocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSaveLocationFragment: Fragment() {

    private lateinit var binding: FragmentLocationSaveSearchBinding
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

        binding.searchField.setOnClickListener {

        }
    }
}