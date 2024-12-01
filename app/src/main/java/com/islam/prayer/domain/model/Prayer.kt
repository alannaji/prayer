package com.islam.prayer.domain.model

import com.islam.prayer.azan.Time
import java.time.LocalTime

sealed class Prayer(val prayerInfo: PrayerInfo){
    data class Fajr(val info: PrayerInfo) : Prayer(info)
    data class Thuhur(val info: PrayerInfo) : Prayer(info)
    data class Assr(val info: PrayerInfo) : Prayer(info)
    data class Maghrib(val info: PrayerInfo) : Prayer(info)
    data class Isha(val info: PrayerInfo) : Prayer(info)

}


data class PrayerInfo(
    val name:String,
    val time:LocalTime = LocalTime.now(),
    val isCurrent:Boolean=false,
    val isNext:Boolean = false,
    val isFinished:Boolean=false,
    val wasPrayed:Boolean = false
)


