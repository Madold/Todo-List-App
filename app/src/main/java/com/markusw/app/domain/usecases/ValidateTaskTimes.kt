package com.markusw.app.domain.usecases

import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import javax.inject.Inject

class ValidateTaskTimes @Inject constructor() {

    operator fun invoke(scheduled: Boolean, hour: Int?, minutes: Int?): ValidationResult {

        val isTimeNull = hour == null || minutes == null

        if(scheduled && isTimeNull) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(id = R.string.time_empty_error_message)
            )
        }

        return ValidationResult(successful = true)
    }

}