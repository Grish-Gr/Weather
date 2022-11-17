package com.example.weather.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.FragmentListWeatherStepBinding
import com.example.weather.view.adapters.StepWeatherAdapter
import com.example.weather.viewmodels.StepWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepWeatherListFragment: Fragment() {

    private lateinit var binding: FragmentListWeatherStepBinding
    private val stepWeatherViewModel: StepWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListWeatherStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initAction()
        initViewModel()
    }

    private fun initRecyclerView(){
        binding.listStepWeather.adapter = StepWeatherAdapter()
        binding.listStepWeather.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun initViewModel(){
        stepWeatherViewModel.listStepWeather.observe(this.viewLifecycleOwner){
            binding.showMoreStepWeather.visibility = if(it.isEmpty()) View.GONE else View.VISIBLE
            binding.showMoreStepWeather.isClickable = true
            if (it.size > countShortInterval){
                (binding.listStepWeather.adapter as StepWeatherAdapter)
                    .addListStepWeather(it.subList(countShortInterval, it.size))
            } else {
                (binding.listStepWeather.adapter as StepWeatherAdapter).setListStepWeather(it)
            }
        }
        stepWeatherViewModel.setErrorAction {
            showToast(R.string.no_internet_access)
        }
    }

    private fun initAction(){
        binding.showMoreStepWeather.setOnClickListener {
            if ((binding.listStepWeather.adapter as StepWeatherAdapter).itemCount <= countShortInterval){
                changeTextActionButton(R.string.hide_list)
                binding.showMoreStepWeather.isClickable = false
                stepWeatherViewModel.getAllListStepWeather{ _: Exception ->
                    showToast(R.string.no_internet_access)
                    binding.showMoreStepWeather.isClickable = true
                    changeTextActionButton(R.string.show_more)
                }
            } else {
                (binding.listStepWeather.adapter as StepWeatherAdapter).removeStepWeather(0, countShortInterval)
                changeTextActionButton(R.string.show_more)
            }
        }
    }

    private fun showToast(@StringRes idResMessage: Int){
        Toast.makeText(this.context, resources.getText(idResMessage), Toast.LENGTH_SHORT).show()
    }

    private fun changeTextActionButton(@StringRes idResText: Int){
        binding.showMoreStepWeather.text = resources.getText(idResText)
    }

    companion object{
        const val countShortInterval = 5
    }
}