package com.markusw.app.domain.usecases

import com.markusw.app.core.utils.DateFormatter
import java.time.LocalDate
import javax.inject.Inject

class FormatDate @Inject constructor() {
    operator fun invoke(date: LocalDate): String {
        return DateFormatter.format(date)
    }

}