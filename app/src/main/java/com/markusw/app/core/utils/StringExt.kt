package com.markusw.app.core.utils

import java.util.Locale

fun String.capitalizeIt(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}