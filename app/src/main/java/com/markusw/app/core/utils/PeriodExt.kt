package com.markusw.app.core.utils

import java.time.Period

fun Period.toMillis(): Long  {
    val days = this.days
    val months = this.months
    val years = this.years
    val millis = days * 24 * 60 * 60 * 1000 + months * 30 * 24 * 60 * 60 * 1000 + years * 365 * 24 * 60 * 60 * 1000

    return millis.toLong()
}