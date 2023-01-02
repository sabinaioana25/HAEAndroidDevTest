package com.example.haeandroiddevtest.fragments.cities

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentMainBinding
import com.example.haeandroiddevtest.fragments.apps.AppListFragment
import com.example.haeandroiddevtest.utils.ViewModelFactory
import com.example.haeandroiddevtest.repository.CitiesRepository


class CityFragment : Fragment() {
    private lateinit var viewModel: CityViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val cityDetailsRepository = CitiesRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application, cityDetailsRepository))[CityViewModel::class.java]
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            varViewModel = viewModel
            viewModel.batteryCharge.observe(viewLifecycleOwner) { newBatteryCharge ->
                tvBattery.text = newBatteryCharge.toString()
            }
            launchButton.setOnClickListener {
                view?.findNavController()?.navigate(R.id.AppListFragment)
            }
            rvCityList.adapter = CityItemAdapter()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}