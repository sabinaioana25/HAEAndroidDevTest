package com.example.haeandroiddevtest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haeandroiddevtest.domain.ItemCity
import com.example.haeandroiddevtest.network.httpRequest
import com.example.haeandroiddevtest.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HAERepository {

    private val _cityData = MutableLiveData<ArrayList<ItemCity>>(arrayListOf())
    val cityData: LiveData<ArrayList<ItemCity>> = _cityData

    suspend fun fetchCities() {
        val cities = withContext(Dispatchers.IO) {
            val cities = arrayListOf<ItemCity>()
            CITIES.forEach { cityName ->
                cities.add(httpRequest("$BASE_URL/$cityName", cityName))
            }
            return@withContext cities
        }
        _cityData.value = cities
    }
}