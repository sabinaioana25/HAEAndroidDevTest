package com.example.haeandroiddevtest.fragments.apps

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haeandroiddevtest.domain.ItemApp
import com.example.haeandroiddevtest.utils.isSystemPackage

class AppListViewModel(val app: Application) : AndroidViewModel(app) {

    private var _apps = MutableLiveData<List<ItemApp>>()
    val apps: LiveData<List<ItemApp>>
        get() = _apps

    init {
        val appList = arrayListOf<ItemApp>()
        app.packageManager.apply {
            getInstalledApplications(PackageManager.GET_META_DATA).forEach {
                if (!getPackageInfo(it.packageName, PackageManager.GET_META_DATA).isSystemPackage())
                    appList.add(
                        ItemApp(
                            getApplicationLabel(
                                getApplicationInfo(it.packageName, 0)
                            ).toString(),
                            it.packageName,
                            getApplicationIcon(it.packageName)
                        )
                    )
            }
        }
        _apps.value = appList
    }
}