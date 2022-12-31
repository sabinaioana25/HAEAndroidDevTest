package com.example.haeandroiddevtest.fragments

import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentCityBinding
import com.example.haeandroiddevtest.network.CityDetailsRepository
import com.example.haeandroiddevtest.utils.checkBatteryCharge
import kotlinx.coroutines.launch

class CityFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var _binding: FragmentCityBinding? = null
    private val binding: FragmentCityBinding
        get() = _binding!!

    val cityDetailsRepository = CityDetailsRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelFactory = ViewModelFactory(context)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            varViewModel = viewModel
            viewModel.batteryCharge.observe(viewLifecycleOwner) { newBatteryCharge ->
                tvBattery.text = newBatteryCharge.toString()
            }
            lifecycleScope.launch {
                val listOfCities = cityDetailsRepository.refreshList()
                Log.i("CityFragment", "$listOfCities")
            }
            launchButton.setOnClickListener {
                context?.let { viewModel.launchApp(it) }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}