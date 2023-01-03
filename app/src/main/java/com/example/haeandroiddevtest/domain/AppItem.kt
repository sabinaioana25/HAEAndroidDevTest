package com.example.haeandroiddevtest.domain

import android.graphics.drawable.Drawable

data class AppItem(
    val appName: String,
    val packageName: String,
    val appIcon: Drawable
)