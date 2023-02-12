package com.markusw.app.domain

sealed class ValidationEvent {
    object Success: ValidationEvent()
}
