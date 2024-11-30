package com.islam.prayer.presentation.main.state

import android.location.Location

sealed class UiState {
    data object Loading:UiState()
    data class Success(val mainState: MainState):UiState()
    data object Error:UiState()
}
data class MainState(
    val location: Location,
    val isDailyQuranEnabled:Boolean = true
)