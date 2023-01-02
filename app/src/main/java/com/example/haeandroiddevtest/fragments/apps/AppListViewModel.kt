package com.example.haeandroiddevtest.fragments.apps

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haeandroiddevtest.utils.PACKAGE_NAME

class AppListViewModel(val app: Application) : AndroidViewModel(app) {

    private var _apps = MutableLiveData<String>()
    val apps: LiveData<String>
        get() = _apps

    init {

    }

    fun launchApp(context: Context): Boolean {
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        if (intent != null) startActivity(context, intent, null)
        else Toast.makeText(context, "Unable to launch app", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCleared() {
        super.onCleared()
    }
}