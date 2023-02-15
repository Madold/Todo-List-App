package com.markusw.app.core.utils

import java.time.Month
import java.time.format.TextStyle
import java.util.*

fun Month.toLocalFormat(): String {
    return this.getDisplayName(
        TextStyle.FULL_STANDALONE,
        Locale.getDefault()
    ).lowercase()
}