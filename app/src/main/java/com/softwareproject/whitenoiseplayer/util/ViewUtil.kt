package com.softwareproject.whitenoiseplayer.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.WindowManager
/*
屏幕适配
 */
fun getStatusBarHeight(resources: Resources): Int {
    val statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (statusBarHeightId > 0) {
        resources.getDimensionPixelSize(statusBarHeightId)
    } else 0
}

fun getScreenWidth(windowManager: WindowManager): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun getScreenHeight(windowManager: WindowManager): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

