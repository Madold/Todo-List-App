package com.markusw.app.domain.usecases

import javax.inject.Inject

class ValidateTaskTitle @Inject constructor() {

    operator fun invoke(title: String): ValidationResult {
        if (title.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Title cannot be empty"
            )
        }

        return ValidationResult(successful = true)
    }

}


