package com.markusw.app.domain

data class AuthenticationResult(
    val success: Boolean,
    val errorMessage: String? = null
)
