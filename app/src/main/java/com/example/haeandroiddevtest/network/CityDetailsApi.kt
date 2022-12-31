package com.example.haeandroiddevtest.network

import com.example.haeandroiddevtest.utils.*
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Path

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CityDetailApiService {
    @GET("{id}")
    suspend fun getCityDetails(@Path("id") id: String): City
}

object CityApi {
    val retrofitService: CityDetailApiService by lazy {
        retrofit.create(CityDetailApiService::class.java)
    }
}