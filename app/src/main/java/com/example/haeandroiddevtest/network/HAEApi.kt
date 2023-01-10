package com.example.haeandroiddevtest.network

import com.example.haeandroiddevtest.domain.ItemCity
import com.example.haeandroiddevtest.utils.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun httpRequest(url: String, cityName: String): ItemCity {
    with(URL(url).openConnection() as HttpURLConnection) {
        requestMethod = "GET" // Optional
        return when (responseCode) {
            in 200..299 -> {
                val jsonResponse = JSONObject(inputStream.bufferedReader().use { it.readText() })
                ItemCity(
                    jsonResponse.get("city") as String,
                    jsonResponse.get("country") as String,
                    jsonResponse.get("temperature") as Int,
                    jsonResponse.get("description") as String
                )
            }
            in 300..399 -> httpRequest(getHeaderField("Location"), cityName)
            else -> ItemCity("no available data for $cityName", "not available", 0, "not available")
        }
    }
}
