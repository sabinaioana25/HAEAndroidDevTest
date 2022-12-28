package com.example.haeandroiddevtest.network

import com.example.haeandroiddevtest.utils.*
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CityDetailApiService {
    @GET(CITY_BEIJING)
    suspend fun getBeijingDetails(): Cities

    @GET(CITY_BERLIN)
    suspend fun getBerlinDetail(): Cities

    @GET(CITY_CARDIFF)
    suspend fun getCardiffDetail(): Cities

    @GET(CITY_EDINBURGH)
    suspend fun getEdinburghDetail(): Cities

    @GET(CITY_LONDON)
    suspend fun getLondonDetails(): Cities

    @GET(CITY_NOTTINGHAM)
    suspend fun getNottinghamDetails(): Cities
}

object CityApi {
    val retrofitService: CityDetailApiService by lazy {
        retrofit.create(CityDetailApiService::class.java)
    }
}
