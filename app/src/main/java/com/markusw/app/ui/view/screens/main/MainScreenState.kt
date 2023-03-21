package com.markusw.app.ui.view.screens.main

import com.markusw.app.ui.view.UiText

data class MainScreenState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isLoading: Boolean = false
)
