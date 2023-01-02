package com.example.haeandroiddevtest.fragments

import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentMainBinding
import com.example.haeandroiddevtest.repository.CityDetailsRepository
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    val appListFragment = AppListFragment()
    private val cityDetailsRepository = CityDetailsRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application, cityDetailsRepository))[MainViewModel::class.java]
        Log.e("MainFragment", appListFragment.toString())
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