package com.example.haeandroiddevtest.network


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CityDetailsRepository {

    suspend fun refreshList(): Cities {
        return withContext(Dispatchers.IO) {
            return@withContext getCityProperties()
        }
    }

    suspend fun getCityProperties(): Cities {
        return withContext(Dispatchers.IO) {
            try {
                Log.i("CityDetailsRepository", CityApi.retrofitService.getBeijingDetails().toString())
                return@withContext CityApi.retrofitService.getBeijingDetails()
//                return@withContext CityApi.retrofitService.getBerlinDetail()
//                return@withContext CityApi.retrofitService.getCardiffDetail()
//                return@withContext CityApi.retrofitService.getEdinburghDetail()
//                return@withContext CityApi.retrofitService.getLondonDetails()
//                return@withContext CityApi.retrofitService.getNottinghamDetails()
            } catch (e: Exception) {
                Log.d("CityDetailsRepository", "Error: $e")
                return@withContext Cities("beijing", "china", 5, "some weather condition")
            }
        }
    }
}