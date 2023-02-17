package com.markusw.app.ui.view

sealed class UiState {
    object Loading: UiState()
    object Rest: UiState()
}
