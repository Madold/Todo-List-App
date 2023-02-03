package com.markusw.app.domain.usecases

import com.markusw.app.core.utils.TimeFormatter
import javax.inject.Inject

class FormatTime @Inject constructor() {
    operator fun invoke(hours: Int, minutes: Int): String {
        return TimeFormatter.formatTime(hours, minutes)
    }
}
