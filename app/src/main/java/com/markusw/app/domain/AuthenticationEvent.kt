package com.markusw.app.domain

sealed class AuthenticationEvent {
    object Success : AuthenticationEvent()
    data class Failure(val reason: String): AuthenticationEvent()
}
