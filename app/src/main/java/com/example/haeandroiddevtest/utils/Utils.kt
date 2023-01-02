package com.example.haeandroiddevtest.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ApplicationInfoFlags
import android.os.BatteryManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

fun displayCurrentTime(): String {
    val formattedDayName = LocalDate.now().dayOfWeek.toString().take(3)
    val time = SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time)
    return "$formattedDayName $time"
}

fun checkBatteryCharge(context: Context): Int {
    val batteryManager = context.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
    return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}

fun getInstalledApps(context: Context) {
//    val appList = context.packageManager.getInstalledPackages(0)
//    val appList = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//    for (i in 0..appList.size) {
//        val packageInfo = appList[0]
//        if (packageInfo!!.applicationInfo.flags and ApplicationInfo.FLAG_INSTALLED == 0) {
//           val appName = packageInfo.applicationInfo.loadLabel(context.packageManager).toString()
//        }
//    }
    val list = ArrayList<String>()
    val appList = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    for (i in 0..appList.size) {
//        list[i] = appList.
    }

}