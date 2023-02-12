package com.markusw.app.core.utils

import java.time.LocalDate
import java.util.*

class DateFormatter {
    companion object {
        fun format(date: LocalDate): String {
            return "${date.dayOfWeek.toString().lowercase().replaceFirstChar { 
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
            }} ${date.dayOfMonth.toString().lowercase()} of ${date.month.toString().lowercase()}"
        }
    }
}