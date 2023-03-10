package com.markusw.app.ui.view.screens.presentation

import com.markusw.app.ui.view.UiText

data class PresentationState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isLoading: Boolean = false
)
