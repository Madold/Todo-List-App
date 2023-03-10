package com.markusw.app.domain.usecases

import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import javax.inject.Inject

class ValidatePassword @Inject constructor() {

    companion object {
        private const val PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{6,}\$"
        private const val MINIMUM_PASSWORD_LENGTH = 6
    }

    operator fun invoke(password: String): ValidationResult {
        if(password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.password_empty_text)
            )
        }

        if(password.length < MINIMUM_PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.password_length_error_text)
            )
        }

        if(!password.matches(Regex(PASSWORD_REGEX))) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.password_not_valid_text)
            )
        }

        return ValidationResult(successful = true)
    }

}