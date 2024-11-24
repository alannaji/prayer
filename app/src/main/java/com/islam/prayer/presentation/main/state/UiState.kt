package com.islam.prayer.presentation.main.state

import android.location.Location

sealed class UiState {
    data object Loading:UiState()
    data class Success(val location:Location):UiState()
    data object Error:UiState()
}