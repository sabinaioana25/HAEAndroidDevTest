package com.example.haeandroiddevtest.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ApplicationInfoFlags
import android.os.BatteryManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

fun displayCurrentTime(): String = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date())

fun Context.checkBatteryCharge() =
    (getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager).getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

fun PackageInfo.isSystemPackage() = (applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0