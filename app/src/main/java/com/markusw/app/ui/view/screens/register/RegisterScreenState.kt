package com.markusw.app.ui.view.screens.register

data class RegisterScreenState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val isLoading: Boolean = false
)
