package com.markusw.app.domain.usecases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FormatTimeTest {

    private lateinit var formatTime: FormatTime

    @Before
    fun setUp() {
        formatTime = FormatTime()
    }

    @Test
    fun `given a hour and a minute the should format it in the format you see below`() {
        val expectedFormat = "12:15 PM"
        val hour = 12
        val minutes = 15
        val result = formatTime(hour, minutes)
        assertEquals(expectedFormat, result)
    }

}