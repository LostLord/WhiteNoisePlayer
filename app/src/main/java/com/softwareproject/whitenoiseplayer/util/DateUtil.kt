package com.softwareproject.whitenoiseplayer.util

import android.util.Log
import java.util.*

enum class DayTime {
    MORNING,
    NOON,
    AFTERNOON,
    NIGHT
}

val calendar: Calendar = Calendar.getInstance()

fun getDayTime(): DayTime {
    return when (calendar.get(Calendar.HOUR_OF_DAY)) {
        in 6..10 -> DayTime.MORNING
        in 11..13 -> DayTime.NOON
        in 14..17 -> DayTime.AFTERNOON
        else -> DayTime.NIGHT
    }
}