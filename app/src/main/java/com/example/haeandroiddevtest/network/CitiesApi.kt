package com.example.haeandroiddevtest.network

import com.example.haeandroiddevtest.domain.City
import com.example.haeandroiddevtest.utils.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun httpRequest(cityName: String): City {

    val url = URL("$BASE_URL/$cityName")
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = "GET"
        return if (responseCode in 200..299) {
            val jsonResponse = JSONObject(inputStream.bufferedReader().use { it.readText() })
            City(
                jsonResponse.get("city").toString(),
                jsonResponse.get("country").toString(),
                jsonResponse.get("temperature").toString().toInt(),
                jsonResponse.get("description").toString()
            )
        } else City("no available data for $cityName", "not available", 0, "not available")
    }
}




