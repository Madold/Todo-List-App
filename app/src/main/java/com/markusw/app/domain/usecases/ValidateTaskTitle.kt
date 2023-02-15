package com.markusw.app.domain.usecases

import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import javax.inject.Inject

class ValidateTaskTitle @Inject constructor() {

    operator fun invoke(title: String): ValidationResult {
        if (title.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.title_error_message)
            )
        }

        return ValidationResult(successful = true)
    }

}


