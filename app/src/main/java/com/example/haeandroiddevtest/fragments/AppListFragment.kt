package com.example.haeandroiddevtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentAppListBinding

class AppListFragment : Fragment() {
//    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentAppListBinding? = null
    private val binding: FragmentAppListBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_list, container, false)
        _binding?.apply {
            rvAppList.adapter = AppItemAdapter()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}