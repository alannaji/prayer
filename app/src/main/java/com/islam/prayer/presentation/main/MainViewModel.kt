package com.islam.prayer.presentation.main

import UiEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.prayer.azan.Azan
import com.islam.prayer.azan.Method
import com.islam.prayer.azan.astrologicalCalc.Location
import com.islam.prayer.azan.astrologicalCalc.SimpleDate
import com.islam.prayer.domain.rep.LocationRep
import com.islam.prayer.presentation.main.state.UiState
import com.islam.prayer.util.AppConstants.TAG
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
    private val locationRep: LocationRep
) :ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun fetchLocation(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val location = locationRep.getCurrentLocation()
            if(location!=null){

                _uiState.value = UiState.Success(location)
                val today = SimpleDate(GregorianCalendar())

                val prayerLoc = Location(location.latitude, location.longitude, -6.0, 0)
                val azan = Azan(prayerLoc, Method.EGYPT_SURVEY)
                val prayerTimes = azan.getPrayerTimes(today)

                Log.d(TAG, "date ---> " + today.day + " / " + today.month + " / " + today.year)

                Log.d(TAG,"Fajr ---> " + prayerTimes.fajr())
                Log.d(TAG,"Zuhr -->" + prayerTimes.thuhr())
                Log.d(TAG,"Asr --->" + prayerTimes.assr())
                Log.d(TAG,"Maghrib --->" + prayerTimes.maghrib())
                Log.d(TAG,"ISHA  --->" + prayerTimes.ishaa())

            }else{
                _uiState.value = UiState.Error
            }
        }
    }

    fun goToSettings(){
        sendEvent(UiEvent.Navigate(Route.SettingsRoute))
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}