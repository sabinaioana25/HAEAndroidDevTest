package com.example.haeandroiddevtest.fragments

import android.content.Context
import android.os.BatteryManager
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haeandroiddevtest.utils.displayCurrentTime

class MainViewModel(context: Context?) : ViewModel() {

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time
    private val _battery = MutableLiveData<String>()
    val battery: LiveData<String> = _battery

    val handler = Handler(Looper.getMainLooper())
    val runnable = object : Runnable {
        override fun run() {
            _time.value = displayCurrentTime()
            handler.postDelayed(this, 1000)
        }
    }

    var batteryManager = context?.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
    var level = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

    init {
        handler.post(runnable)
        _battery.value = "$level%"
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(runnable)
    }
}
