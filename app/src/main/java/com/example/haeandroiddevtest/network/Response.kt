package com.example.haeandroiddevtest.network

data class City(
    val city: String,
    val country: String,
    val temperature: Int,
    val description: String
)

data class AppItem(
    val appName: String,
    val packageName: String
)