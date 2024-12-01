package com.islam.prayer.presentation.main

import UiEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.prayer.azan.Azan
import com.islam.prayer.azan.AzanTimes
import com.islam.prayer.azan.Method
import com.islam.prayer.azan.astrologicalCalc.Location
import com.islam.prayer.azan.astrologicalCalc.SimpleDate
import com.islam.prayer.domain.model.Prayer
import com.islam.prayer.domain.model.PrayerInfo
import com.islam.prayer.domain.rep.LocationRep
import com.islam.prayer.presentation.main.state.MainState
import com.islam.prayer.presentation.main.state.UiState
import com.islam.prayer.presentation.main.util.PrayerManager
import com.islam.prayer.presentation.main.util.PrayerType
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
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun initialize() {

        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val location = locationRep.getCurrentLocation()

            if (location != null) {

                val today = SimpleDate(GregorianCalendar())
                val prayerLoc = Location(location.latitude, location.longitude, -6.0, 0)
                val azan = Azan(prayerLoc, Method.NORTH_AMERICA)
                val prayerTimes: AzanTimes = azan.getPrayerTimes(today)
                val prayerManager = PrayerManager(prayerTimes)
                val prayers: List<Prayer> = PrayerType.entries.map { prayerType->
                    val prayerInfo = PrayerInfo(
                        name = prayerType.displayName,
                        time = prayerManager.prayerTimesMap[prayerType]!!,
                        isCurrent = prayerManager.isCurrent(prayerType),
                        isFinished = prayerManager.isFinished(prayerType)
                    )
                    when(prayerType){
                        PrayerType.FAJR -> Prayer.Fajr(prayerInfo)
                        PrayerType.THUHR -> Prayer.Thuhur(prayerInfo)
                        PrayerType.ASSR -> Prayer.Assr(prayerInfo)
                        PrayerType.MAGHRIB -> Prayer.Maghrib(prayerInfo)
                        PrayerType.ISHAA -> Prayer.Isha(prayerInfo)
                    }
                }

                val mainState = MainState(
                    prayers = prayers
                )
                _uiState.value = UiState.Success(mainState)
            } else {
                _uiState.value = UiState.Error
            }
        }
    }
}


/*val zuhur: Time = prayerTimes.thuhr()

Log.d(TAG,"**********************")
Log.d(TAG, "date ---> " + today.day + " / " + today.month + " / " + today.year)

Log.d(TAG,"Fajr ---> " + prayerTimes.fajr())
Log.d(TAG,"Zuhr -->" + prayerTimes.thuhr())
Log.d(TAG,"Assr --->" + prayerTimes.assr())
Log.d(TAG,"Maghrib --->" + prayerTimes.maghrib())
Log.d(TAG,"ISHA  --->" + prayerTimes.ishaa())*/
/*    private fun isFeatureEnabled(toggleKey: String): Boolean {
        val DAILY_QURAN_ENABLED = ldTogglesRep.isFeatureEnabled(toggleKey)

        Log.d("alaa", "DAILY_QURAN_ENABLED is $DAILY_QURAN_ENABLED")

        return DAILY_QURAN_ENABLED
    }*/