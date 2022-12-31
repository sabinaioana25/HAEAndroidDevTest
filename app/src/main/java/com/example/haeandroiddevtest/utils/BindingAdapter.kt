package com.example.haeandroiddevtest.utils

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haeandroiddevtest.fragments.CityItemAdapter
import com.example.haeandroiddevtest.network.City
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@BindingAdapter("bindCityData")
fun RecyclerView.bindCityData(cities: MutableList<City>?) = cities?.let {
    (adapter as CityItemAdapter).submitList(cities)
}

@BindingAdapter("bindCityLayout")
fun LinearLayout.bindCityLayout(city: City) {
    (getChildAt(0) as TextView).text = city.city
    (getChildAt(1) as TextView).text = city.country
    (getChildAt(2) as TextView).text = city.temperature.toString()
    (getChildAt(3) as TextView).text = city.description
}
