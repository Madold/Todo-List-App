package com.markusw.app.core.utils

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

object DelayComputer {
    fun computeDelay(endDate: LocalDate, endHour: Int, endMinutes: Int): Long {
        val now = LocalDate.now()
        val dateDifference = now.until(endDate)
        val currentTime = LocalTime.now()
        val timeDifference = Duration.between(
            currentTime,
            LocalTime.of(endHour, endMinutes)
        )

        return dateDifference.toMillis() + timeDifference.toMillis()
    }
}