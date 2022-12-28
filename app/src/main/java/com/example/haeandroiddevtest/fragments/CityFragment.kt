package com.example.haeandroiddevtest.fragments

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentCityBinding

class CityFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var _binding: FragmentCityBinding? = null
    private val binding: FragmentCityBinding
        get() = _binding!!

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
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}