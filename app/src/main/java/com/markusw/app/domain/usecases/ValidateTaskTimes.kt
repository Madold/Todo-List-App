package com.markusw.app.domain.usecases

import javax.inject.Inject

class ValidateTaskTimes @Inject constructor() {

    operator fun invoke(scheduled: Boolean, hour: Int?, minutes: Int?): ValidationResult {

        val isTimeNull = hour == null && minutes == null

        if(scheduled && isTimeNull) {
            return ValidationResult(
                successful = false,
                errorMessage = "Time cannot be empty"
            )
        }

        return ValidationResult(successful = true)
    }

}