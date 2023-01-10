package com.example.haeandroiddevtest.fragments.cities

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.haeandroiddevtest.domain.ItemCity
import com.example.haeandroiddevtest.repository.HAERepository
import com.example.haeandroiddevtest.utils.checkBatteryCharge
import com.example.haeandroiddevtest.utils.displayCurrentTime
import kotlinx.coroutines.launch

class CityViewModel(val app: Application, private val cityDetailsRepository: HAERepository) : AndroidViewModel(app) {

    private val _navigateToFragment = MutableLiveData<Boolean>()
    val navigateToFragment: LiveData<Boolean>
        get() = _navigateToFragment
    fun onButtonClick() {
        _navigateToFragment.value = true
    }
    fun onFragmentNavigated() {
        _navigateToFragment.value = false
    }

    val cities: LiveData<ArrayList<ItemCity>> = cityDetailsRepository.cityData

    private var _time = MutableLiveData<String>()
    val time: LiveData<String>
        get() = _time
    private var _batteryCharge = MutableLiveData<Int>()
    val batteryCharge: LiveData<Int>
        get() = _batteryCharge

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
            cityDetailsRepository.fetchCities()
        }
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(runnable)
    }
}