package com.example.haeandroiddevtest.fragments.apps

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haeandroiddevtest.domain.AppItem

class AppListViewModel(val app: Application) : AndroidViewModel(app) {

    private var _apps = MutableLiveData<List<AppItem>>()
    val apps: LiveData<List<AppItem>>
        get() = _apps

    init {
        val appList = arrayListOf<AppItem>()
        app.packageManager.apply {
            getInstalledApplications(PackageManager.GET_META_DATA).forEach {
                appList.add(
                    AppItem(
                        getApplicationLabel(getApplicationInfo(it.packageName, 0)).toString(),
                        it.packageName,
                        getApplicationIcon(it.packageName)
                    )
                )
            }
        }
        _apps.value = appList
    }
}