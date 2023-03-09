package com.markusw.app.ui.view.screens.presentation

data class PresentationState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)
