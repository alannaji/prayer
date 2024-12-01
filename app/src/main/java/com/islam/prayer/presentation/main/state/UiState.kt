package com.islam.prayer.presentation.main.state

sealed class UiState {
    data object Loading:UiState()
    data class Success(val mainState: MainState):UiState()
    data object Error:UiState()
}
