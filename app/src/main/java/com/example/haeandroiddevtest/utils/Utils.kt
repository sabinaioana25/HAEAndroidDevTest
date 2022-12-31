package com.example.haeandroiddevtest.utils

import android.content.Context
import android.os.BatteryManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
//import com.example.haeandroiddevtest.receivers.BatteryReceiver
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun displayCurrentTime(): String {
    val formattedDayName = LocalDate.now().dayOfWeek.toString().take(3)
    val time = SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time)
    return "$formattedDayName $time"
}

fun checkBatteryCharge(context: Context): Int {
    val batteryManager = context.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
    return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}

//fun launchApp(context: Context, packageName: String) {
//    val intent =
//        context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
//    if (intent != null) startActivity(intent)
//    else Toast.makeText(context, "Unable to launch app", Toast.LENGTH_SHORT).show()
//}