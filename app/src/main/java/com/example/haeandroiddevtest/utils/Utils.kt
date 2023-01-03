package com.example.haeandroiddevtest.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

fun displayCurrentTime(): String = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date())

fun Context.checkBatteryCharge() =
    (getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager).getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

fun PackageInfo.isSystemPackage() = (applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0