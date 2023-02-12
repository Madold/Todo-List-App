package com.markusw.app.domain.usecases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
