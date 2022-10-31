package com.example.weather.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weather.R
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
        initEditText()
        initAction()
    }

    private fun initEditText(){
        binding.searchField.editText?.addTextChangedListener{

        }
    }

    private fun initAction(){
        binding.addLocation.setOnClickListener {
            if (viewModel.checkInternetConnection(this.context as Context)){
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container_location_fragment, SearchLocationFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(this.context, getString(R.string.no_internet_access), Toast.LENGTH_SHORT).show()
            }
        }
    }
}