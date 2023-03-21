package com.markusw.app.domain

sealed class SignOutEvent {
    object Success : SignOutEvent()
}
