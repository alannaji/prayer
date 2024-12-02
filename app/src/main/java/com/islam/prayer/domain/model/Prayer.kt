package com.islam.prayer.domain.model

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

enum class PrayerType(val displayName:String) {
    FAJR("Fajr"),
    THUHR("Thuhr"),
    ASSR("Assr"),
    MAGHRIB("Maghrib"),
    ISHAA("Ishaa");

    fun toPrayer(info: PrayerInfo): Prayer {
        return when (this) {
            FAJR -> Prayer.Fajr(info)
            THUHR -> Prayer.Thuhur(info)
            ASSR -> Prayer.Assr(info)
            MAGHRIB -> Prayer.Maghrib(info)
            ISHAA -> Prayer.Isha(info)
        }
    }
}



