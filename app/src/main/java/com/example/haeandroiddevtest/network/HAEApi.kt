package com.example.haeandroiddevtest.network

import com.example.haeandroiddevtest.domain.ItemCity
import com.example.haeandroiddevtest.utils.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun httpRequest(cityName: String): ItemCity {

    val url = URL("$BASE_URL/$cityName")
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = "GET"
        return if (responseCode in 200..299) {
            val jsonResponse = JSONObject(inputStream.bufferedReader().use { it.readText() })
            ItemCity(
                jsonResponse.get("city").toString(),
                jsonResponse.get("country").toString(),
                jsonResponse.get("temperature").toString().toInt(),
                jsonResponse.get("description").toString()
            )
        } else ItemCity("no available data for $cityName", "not available", 0, "not available")
    }
}