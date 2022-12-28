package com.example.haeandroiddevtest.utils

import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.haeandroiddevtest.fragments.CityFragment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt

fun displayCurrentTime(): String {
    val formattedDayName = LocalDate.now().dayOfWeek.toString().take(3)
    val time = SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time)
    return "$formattedDayName $time"
}


