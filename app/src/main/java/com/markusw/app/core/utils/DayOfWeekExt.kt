package com.markusw.app.core.utils

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

fun DayOfWeek.toLocalFormat(): String {
    return this.getDisplayName(
        TextStyle.FULL_STANDALONE,
        Locale.getDefault()
    ).lowercase()
}