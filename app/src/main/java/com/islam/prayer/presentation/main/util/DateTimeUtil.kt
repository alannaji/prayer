package com.islam.prayer.presentation.main.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    private val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

    fun formatLocalTime(localTime: LocalTime): String {
        return localTime.format(timeFormatter)
    }
}