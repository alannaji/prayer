package com.islam.prayer.presentation.main

import UiEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.prayer.domain.model.Prayer
import com.islam.prayer.domain.util.PrayerManager
import com.islam.prayer.domain.util.PrayerManagerFactory
import com.islam.prayer.presentation.main.state.MainState
import com.islam.prayer.presentation.main.state.UiState
import com.islam.prayer.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prayerManagerFactory: PrayerManagerFactory
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private lateinit var prayerManager: PrayerManager

    fun initialize() {

        viewModelScope.launch {
            reduceState { UiState.Loading }

            runCatching {
                if (!::prayerManager.isInitialized) {
                    prayerManager = prayerManagerFactory.createPrayerManager()
                }
                val prayers: List<Prayer> = prayerManager.mapPrayerTimesToPrayers()
                MainState(prayers = prayers)

            }.onSuccess { mainState ->
                reduceState { UiState.Success(mainState) }
            }.onFailure { error ->
                reduceState {
                    logError(error)
                    UiState.Error(
                        message = error.message ?: "An unknown error occurred",
                        throwable = error
                    )
                }
            }
        }
    }

    private fun reduceState(action: (UiState) -> UiState) {
        _uiState.value = action(_uiState.value)
    }

    private fun logError(error: Throwable) {
        Log.d(AppConstants.TAG, "Error occurred: ${error.message}") // Replace with proper logging
    }
}
