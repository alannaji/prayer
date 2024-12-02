package com.islam.prayer.domain.util

import android.util.Log
import com.islam.prayer.azan.Azan
import com.islam.prayer.azan.AzanTimes
import com.islam.prayer.azan.Method
import com.islam.prayer.azan.astrologicalCalc.Location
import com.islam.prayer.azan.astrologicalCalc.SimpleDate
import com.islam.prayer.domain.rep.LocationRep
import com.islam.prayer.util.AppConstants.TAG
import java.util.GregorianCalendar
import javax.inject.Inject

class PrayerManagerFactory @Inject constructor(
    private val locationRep: LocationRep
) {
    suspend fun createPrayerManager():PrayerManager {

        return runCatching {
            val location = locationRep.getCurrentLocation() ?: throw IllegalArgumentException("Location Not Available")
            val today = SimpleDate(GregorianCalendar())
            val prayerLoc = Location(location.latitude, location.longitude, -6.0, 0)
            val azan = Azan(prayerLoc, Method.NORTH_AMERICA)
            val prayerTimes: AzanTimes = azan.getPrayerTimes(today)

            PrayerManager(prayerTimes)
        }.getOrElse { exception: Throwable ->
            Log.d(TAG,"Error in creating PrayerManager: ${exception.message}")
            throw IllegalStateException("Failed to create PrayerManager", exception)
        }
    }
}