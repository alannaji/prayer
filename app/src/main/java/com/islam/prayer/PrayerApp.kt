package com.islam.prayer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PrayerApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
