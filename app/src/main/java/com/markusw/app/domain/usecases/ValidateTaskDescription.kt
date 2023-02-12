package com.markusw.app.domain.usecases

import javax.inject.Inject

class ValidateTaskDescription @Inject constructor() {

    operator fun invoke(description: String): ValidationResult {
        if (description.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Description cannot be empty"
            )
        }

        return ValidationResult(successful = true)
    }
}