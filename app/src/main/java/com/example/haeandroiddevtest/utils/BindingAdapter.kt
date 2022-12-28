package com.example.haeandroiddevtest.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@BindingAdapter("currentTime")
fun TextView.crtTime(rand: Int) {
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT)
    val calendar = Calendar.getInstance()
    val day = LocalDate.now().dayOfWeek
    val formattedDayName = day.toString().take(3)
    val time = simpleDateFormat.format(calendar.time)
    text =  "$formattedDayName $time"
}