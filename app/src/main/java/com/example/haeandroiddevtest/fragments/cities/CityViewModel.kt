package com.example.haeandroiddevtest.fragments.cities

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.haeandroiddevtest.domain.City
import com.example.haeandroiddevtest.repository.HAERepository
import com.example.haeandroiddevtest.utils.checkBatteryCharge
import com.example.haeandroiddevtest.utils.displayCurrentTime
import kotlinx.coroutines.launch

class CityViewModel(val app: Application, private val cityDetailsRepository: HAERepository) : AndroidViewModel(app) {

    private var _time = MutableLiveData<String>()
    val time: LiveData<String>
        get() = _time
    private var _batteryCharge = MutableLiveData<Int>()
    val batteryCharge: LiveData<Int>
        get() = _batteryCharge

    val cities: LiveData<ArrayList<City>> = cityDetailsRepository.cityData

    val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            _time.value = displayCurrentTime()
            _batteryCharge.value = app.checkBatteryCharge()
            handler.postDelayed(this, 1000)
        }
    }

    init {
        handler.post(runnable)
        viewModelScope.launch {
            cityDetailsRepository.refreshList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(runnable)
    }
}