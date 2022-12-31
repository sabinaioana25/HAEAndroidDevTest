package com.example.haeandroiddevtest.fragments

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haeandroiddevtest.network.City
import com.example.haeandroiddevtest.network.CityDetailsRepository
import com.example.haeandroiddevtest.utils.PACKAGE_NAME

import com.example.haeandroiddevtest.utils.checkBatteryCharge
import com.example.haeandroiddevtest.utils.displayCurrentTime
import kotlinx.coroutines.launch

class MainViewModel(val context: Context, private val cityDetailsRepository: CityDetailsRepository) : ViewModel() {

    private var _time = MutableLiveData<String>()
    val time: LiveData<String>
        get() = _time
    private var _batteryCharge = MutableLiveData<Int>()
    val batteryCharge: LiveData<Int>
        get() = _batteryCharge

    val handler = Handler(Looper.getMainLooper())
    val batteryHandler = Handler(Looper.getMainLooper())
    private var _cities = MutableLiveData<ArrayList<City>>()
    val cities: LiveData<ArrayList<City>> = _cities

    private val runnable = object : Runnable {
        override fun run() {
            _time.value = displayCurrentTime()
            handler.postDelayed(this, 1000)
            _batteryCharge.value = checkBatteryCharge(context)
            batteryHandler.postDelayed(this, 5000)
        }
    }

    init {
        handler.post(runnable)
        batteryHandler.post(runnable)
        viewModelScope.launch {
            _cities.value = cityDetailsRepository.refreshList()
            Log.i("MainViewModel", "${cities.value}")
        }
    }

    fun launchApp(context: Context): Boolean {
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        if (intent != null) startActivity(context, intent, null)
        else Toast.makeText(context, "Unable to launch app", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(runnable)
        batteryHandler.removeCallbacks(runnable)
    }
}