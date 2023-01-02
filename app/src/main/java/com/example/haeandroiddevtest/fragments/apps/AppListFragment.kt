package com.example.haeandroiddevtest.fragments.apps

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {

        viewModel = ViewModelProvider(this,ViewModelFactory(requireActivity().application, null))[AppListViewModel::class.java]
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_list, container, false)

//        val appList = ArrayList<AppItem>()
//        val flags = PackageManager.ResolveInfoFlags.of(0)
//        val launchIntent = Intent(Intent.ACTION_MAIN, null)
//        val launcherChooser = launchIntent.addCategory(Intent.CATEGORY_LAUNCHER)
//        val activities = requireContext().packageManager.queryIntentActivities(launcherChooser, flags)
//        for (resolveInfo in activities) {
//            if (resolveInfo.activityInfo.packageName == requireContext().packageName)
//                continue
//            val app = AppItem(
//                resolveInfo.loadLabel(requireContext().packageManager).toString(),
//                resolveInfo.activityInfo.packageName
//            )
//            Log.e("AppListFragment", "$appList")
//            appList.add(app)
//        }

//        _binding?.apply {
//            rvAppList.adapter = AppListItemAdapter().also {
//                it.updateAppList(appList.sortedWith { o1, o2 ->
//                    o1?.appName?.compareTo(o2?.appName ?: "", true) ?: 0
//                })
//            }
//        }
//        launchApp(context)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}