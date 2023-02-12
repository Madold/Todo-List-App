package com.markusw.app.domain.usecases

import javax.inject.Inject

class ValidateTaskTimes @Inject constructor() {

    operator fun invoke(scheduled: Boolean, hour: Int?, minutes: Int?): ValidationResult {
        if(scheduled && hour == null || minutes == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "Time cannot be empty"
            )
        }

        return ValidationResult(successful = true)
    }

}