package com.markusw.app.domain.usecases

import com.markusw.app.ui.view.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)
