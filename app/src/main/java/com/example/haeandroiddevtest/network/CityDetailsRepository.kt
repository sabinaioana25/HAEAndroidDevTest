package com.example.haeandroiddevtest.network


import android.util.Log
import com.example.haeandroiddevtest.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CityDetailsRepository {

    suspend fun refreshList(): ArrayList<City> {
        return withContext(Dispatchers.IO) {
            val cities = arrayListOf<City>()
            CITIES.forEach { city ->
                cities.add(getCityDetails(city))
            }
            return@withContext cities
        }
    }

    private suspend fun getCityDetails(city: String): City {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext CityApi.retrofitService.getCityDetails(city)
            } catch (e: Exception) {
                Log.d("CityDetailsRepository", "Error: $e")
                return@withContext City(city, "$e", 0, "$e")
            }
        }
    }
}
