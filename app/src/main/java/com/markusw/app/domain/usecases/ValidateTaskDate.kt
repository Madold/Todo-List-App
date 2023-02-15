package com.markusw.app.domain.usecases

import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import java.time.LocalDate
import javax.inject.Inject

class ValidateTaskDate @Inject constructor() {
    operator fun invoke(scheduled: Boolean, date: LocalDate?): ValidationResult {

        if(scheduled && date == null) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(id = R.string.date_empty_error_message)
            )
        }

        return ValidationResult(successful = true)
    }
}
