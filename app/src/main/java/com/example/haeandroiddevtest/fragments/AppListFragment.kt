package com.example.haeandroiddevtest.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.haeandroiddevtest.R
import com.example.haeandroiddevtest.databinding.FragmentAppListBinding
import com.example.haeandroiddevtest.network.AppItem

class AppListFragment : Fragment() {
    private var _binding: FragmentAppListBinding? = null
    private val binding: FragmentAppListBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_list, container, false)

        val appList = ArrayList<AppItem>()
        val flags = PackageManager.ResolveInfoFlags.of(0)
        val launchIntent = Intent(Intent.ACTION_MAIN, null)
        val launcherChooser = launchIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val activities = requireContext().packageManager.queryIntentActivities(launcherChooser, flags)
        for (resolveInfo in activities) {
            if (resolveInfo.activityInfo.packageName == requireContext().packageName)
                continue
            val app = AppItem(
                resolveInfo.loadLabel(requireContext().packageManager).toString(),
                resolveInfo.activityInfo.packageName
            )
            appList.add(app)
        }
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