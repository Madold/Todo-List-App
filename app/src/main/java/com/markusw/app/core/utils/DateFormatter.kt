package com.markusw.app.core.utils

import java.time.LocalDate

class DateFormatter {
    companion object {
        fun format(date: LocalDate): String {
            return "${
                date.dayOfWeek.toLocalFormat().capitalizeIt()
            } ${date.month.toLocalFormat()} ${date.dayOfMonth.toString().lowercase()}"
        }
    }
}

