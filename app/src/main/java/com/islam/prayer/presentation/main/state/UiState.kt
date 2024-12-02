package com.islam.prayer.presentation.main.state

sealed class UiState {
    data object Loading:UiState()
    data class Success(val mainState: MainState):UiState()
    data class Error(
        val message:String?=null,
        val throwable: Throwable?=null
    ):UiState()
}
