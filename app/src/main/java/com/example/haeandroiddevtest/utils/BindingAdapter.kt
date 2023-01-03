package com.example.haeandroiddevtest.utils

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haeandroiddevtest.fragments.apps.AppListItemAdapter
import com.example.haeandroiddevtest.fragments.cities.CityItemAdapter
import com.example.haeandroiddevtest.domain.AppItem
import com.example.haeandroiddevtest.domain.City
import java.util.*

@BindingAdapter("bindCityData")
fun RecyclerView.bindCityData(cities: MutableList<City>?) = cities?.let {
    (adapter as CityItemAdapter).submitList(cities)
}

@BindingAdapter("bindCityLayout")
fun ConstraintLayout.bindCityLayout(city: City) {
    (getChildAt(0) as TextView).text = city.city
    (getChildAt(1) as TextView).text = city.country
    (getChildAt(2) as TextView).text = city.temperature.toString()
    (getChildAt(3) as TextView).text = city.description
}

@BindingAdapter("bindAppData")
fun RecyclerView.bindAppData(appItems: List<AppItem>?) = appItems?.let {
    (adapter as AppListItemAdapter).submitList(appItems)
}

@BindingAdapter("bindSingleAppInfo")
fun ConstraintLayout.bindSingleAppInfo(appItem: AppItem) {
    (getChildAt(0) as ImageView).background = appItem.appIcon
    (getChildAt(1) as TextView).text = appItem.appName
    (getChildAt(2) as TextView).text = appItem.packageName
}