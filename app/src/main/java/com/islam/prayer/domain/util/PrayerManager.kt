package com.islam.prayer.domain.util

import com.islam.prayer.azan.AzanTimes
import com.islam.prayer.azan.Time
import com.islam.prayer.domain.model.Prayer
import com.islam.prayer.domain.model.PrayerInfo
import com.islam.prayer.domain.model.PrayerType
import java.time.LocalTime

class PrayerManager(
    private val azanTimes: AzanTimes
) {
    private val currentTime: LocalTime = LocalTime.now()

    private val prayerTimesMap: Map<PrayerType, LocalTime> by lazy {
        mapOf(
            PrayerType.FAJR to convertToLocalTime(azanTimes.fajr()),
            PrayerType.THUHR to convertToLocalTime(azanTimes.thuhr()),
            PrayerType.ASSR to convertToLocalTime(azanTimes.assr()),
            PrayerType.MAGHRIB to convertToLocalTime(azanTimes.maghrib()),
            PrayerType.ISHAA to convertToLocalTime(azanTimes.ishaa())
        )
    }
    private val nextPrayerTimesMap: Map<PrayerType, LocalTime> by lazy {
        mapOf(
            PrayerType.FAJR to convertToLocalTime(azanTimes.shuruq()),
            PrayerType.THUHR to convertToLocalTime(azanTimes.assr()),
            PrayerType.ASSR to convertToLocalTime(azanTimes.maghrib()),
            PrayerType.MAGHRIB to convertToLocalTime(azanTimes.ishaa()),
            PrayerType.ISHAA to convertToLocalTime(azanTimes.fajr())
        )
    }

    fun mapPrayerTimesToPrayers(): List<Prayer> {
        return PrayerType.entries.map { prayerType ->
            val prayerInfo = PrayerInfo(
                name = prayerType.displayName,
                time = prayerTimesMap[prayerType]!!,
                isCurrent = isCurrent(prayerType),
                isFinished = isFinished(prayerType)
            )
            prayerType.toPrayer(prayerInfo)
        }
    }

    private fun isCurrent(prayerType: PrayerType): Boolean {
        val startTime = prayerTimesMap[prayerType] ?: return false
        val endTime = nextPrayerTimesMap[prayerType] ?: return false

        return isBetween(startTime, endTime)
    }

    private fun isFinished(prayer: PrayerType): Boolean {
        val endTime = nextPrayerTimesMap[prayer] ?: return false
        return if (prayer == PrayerType.ISHAA)
            false
        else
            currentTime.isAfter(endTime)
    }

    private fun isBetween(startTime: LocalTime, endTime: LocalTime): Boolean {
        return if (startTime.isBefore(endTime)) {
            currentTime.isAfter(startTime) && currentTime.isBefore(endTime)
        } else {
            // For cases like Ishaa -> Fajr
            currentTime.isAfter(startTime) || currentTime.isBefore(endTime)
        }
    }

    private fun convertToLocalTime(time: Time): LocalTime {
        return LocalTime.of(time.hour, time.minute)
    }
}