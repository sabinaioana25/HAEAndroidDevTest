package com.example.haeandroiddevtest.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
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

tailrec fun Context.getActivity(): Activity? = this as? Activity
    ?: (this as? ContextWrapper)?.baseContext?.getActivity()