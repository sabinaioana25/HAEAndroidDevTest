package com.example.haeandroiddevtest.fragments.apps

import android.content.ActivityNotFoundException
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentAppListBinding
import com.example.haeandroiddevtest.domain.*
import com.example.haeandroiddevtest.utils.ViewModelFactory

class AppListFragment : Fragment() {
    private lateinit var viewModel: AppListViewModel
    private var _binding: FragmentAppListBinding? = null
    private val binding: FragmentAppListBinding
        get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application, null))[AppListViewModel::class.java]
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_list, container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            varAppViewModel = viewModel

            rvAppList.adapter = AppListItemAdapter(AppItemListener { appItem ->
                try {
                    startActivity(requireActivity().packageManager.getLaunchIntentForPackage(appItem.packageName))
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            })
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}