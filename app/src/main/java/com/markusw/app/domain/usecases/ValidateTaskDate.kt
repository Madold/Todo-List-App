package com.markusw.app.domain.usecases

import java.time.LocalDate
import javax.inject.Inject

class ValidateTaskDate @Inject constructor() {
    operator fun invoke(scheduled: Boolean, date: LocalDate?): ValidationResult {

        if(scheduled && date == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "Date cannot be empty"
            )
        }

        return ValidationResult(successful = true)
    }
}
