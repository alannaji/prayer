package com.islam.prayer.presentation.main

import UiEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.prayer.azan.astrologicalCalc.Location
import com.islam.prayer.azan.astrologicalCalc.SimpleDate
import com.islam.prayer.domain.rep.LDTogglesRep
import com.islam.prayer.domain.rep.LocationRep
import com.islam.prayer.presentation.main.state.MainState
import com.islam.prayer.presentation.main.state.UiState
import com.islam.prayer.util.nav.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.GregorianCalendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationRep: LocationRep,
    private val ldTogglesRep: LDTogglesRep
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun initialize() {

        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val location = locationRep.getCurrentLocation()
            val isDailyQuranEnabled = ldTogglesRep.isFeatureEnabled("DAILY_QURAN_ENABLED")
            Log.d("alaa", "DAILY_QURAN_ENABLED is $isDailyQuranEnabled")

            if (location != null) {
                val mainState = MainState(
                    location = location,
                    isDailyQuranEnabled = isDailyQuranEnabled
                )
                _uiState.value = UiState.Success(mainState)

                val today = SimpleDate(GregorianCalendar())
                val prayerLoc = Location(location.latitude, location.longitude, -6.0, 0)

            } else {
                _uiState.value = UiState.Error
            }
        }
    }

    private fun isFeatureEnabled(toggleKey: String): Boolean {
        val DAILY_QURAN_ENABLED = ldTogglesRep.isFeatureEnabled(toggleKey)

        Log.d("alaa", "DAILY_QURAN_ENABLED is $DAILY_QURAN_ENABLED")

        return DAILY_QURAN_ENABLED
    }

    fun goToSettings() {
        sendEvent(UiEvent.Navigate(Route.SettingsRoute))
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

/*

                val azan = Azan(prayerLoc, Method.EGYPT_SURVEY)
                val prayerTimes = azan.getPrayerTimes(today)
  Log.d(TAG,"**********************")
       Log.d(TAG, "date ---> " + today.day + " / " + today.month + " / " + today.year)

       Log.d(TAG,"Fajr ---> " + prayerTimes.fajr())
       Log.d(TAG,"Zuhr -->" + prayerTimes.thuhr())
       Log.d(TAG,"Asr --->" + prayerTimes.assr())
       Log.d(TAG,"Maghrib --->" + prayerTimes.maghrib())
       Log.d(TAG,"ISHA  --->" + prayerTimes.ishaa())*/