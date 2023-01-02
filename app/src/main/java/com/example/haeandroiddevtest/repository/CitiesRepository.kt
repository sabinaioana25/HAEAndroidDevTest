package com.example.haeandroiddevtest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haeandroiddevtest.domain.City
import com.example.haeandroiddevtest.network.httpRequest
import com.example.haeandroiddevtest.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CitiesRepository {

    private val _cityData = MutableLiveData<ArrayList<City>>(arrayListOf())
    val cityData: LiveData<ArrayList<City>> = _cityData

    suspend fun refreshList() {
        val cities = withContext(Dispatchers.IO) {
            val cities = arrayListOf<City>()
            CITIES.forEach { cityName ->
                cities.add(httpRequest(cityName))
            }
            return@withContext cities
        }
        _cityData.value = cities
    }
}