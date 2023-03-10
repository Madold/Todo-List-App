package com.markusw.app.domain.usecases

import android.util.Patterns
import com.markusw.app.ui.view.UiText
import javax.inject.Inject
import com.markusw.app.R

class ValidateEmail @Inject constructor() {

    operator fun invoke(email: String): ValidationResult {

        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.email_empty_text)
            )
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.invalid_email_text)
            )
        }

        return ValidationResult(successful = true)
    }

}