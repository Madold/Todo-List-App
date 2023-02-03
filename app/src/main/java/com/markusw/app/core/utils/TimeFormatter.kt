package com.markusw.app.core.utils

class TimeFormatter {
    companion object {
        fun formatTime(hours: Int, minutes: Int): String {

            val hoursString = if(hours > 12) {
                "${hours - 12}"
            } else {
                "$hours"
            }

            val minutesString = if(minutes < 10) {
                "0$minutes"
            } else {
                "$minutes"
            }

            return "$hoursString:$minutesString ${if(hours > 12) "PM" else "AM"}"
        }
    }
}