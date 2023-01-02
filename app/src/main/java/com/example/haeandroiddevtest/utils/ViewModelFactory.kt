package com.example.haeandroiddevtest.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haeandroiddevtest.fragments.cities.CityViewModel
import com.example.haeandroiddevtest.repository.CitiesRepository

class ViewModelFactory(private val app: Application, private val repository: CitiesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return  CityViewModel(app, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}