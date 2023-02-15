package com.markusw.app.domain.usecases

import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import javax.inject.Inject

class ValidateTaskDescription @Inject constructor() {

    operator fun invoke(description: String): ValidationResult {
        if (description.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.description_empty_error_message)
            )
        }

        return ValidationResult(successful = true)
    }
}