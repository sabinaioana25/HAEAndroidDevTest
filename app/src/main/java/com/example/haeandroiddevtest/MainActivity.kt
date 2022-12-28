package com.example.haeandroiddevtest

import android.Manifest.permission.BATTERY_STATS
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.haeandroiddevtest.databinding.ActivityMainBinding
import com.example.haeandroiddevtest.network.CityDetailsRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import com.example.haeandroiddevtest.utils.DATE_FORMAT
import com.example.haeandroiddevtest.utils.PACKAGE_NAME
import kotlinx.coroutines.launch
import android.content.pm.PackageManager
import com.example.haeandroiddevtest.utils.REQUEST_CODE_PERMISSION

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val cityDetailsRepository = CityDetailsRepository()
        lifecycleScope.launch {
            var listOfCities = cityDetailsRepository.refreshList()
            Log.i("MainActivity", "$listOfCities")
        }
    }

    fun launchApp() {
        val packageName = PACKAGE_NAME
        val launchIntent =
            packageManager.getLaunchIntentForPackage(packageName)
        if (launchIntent != null) startActivity(launchIntent)
        else Toast.makeText(this, "Unable to launch app", Toast.LENGTH_SHORT).show()
    }
}