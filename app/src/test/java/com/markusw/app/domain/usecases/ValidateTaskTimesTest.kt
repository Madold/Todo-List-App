package com.markusw.app.domain.usecases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidateTaskTimesTest {

    private lateinit var validateTaskTimes: ValidateTaskTimes

    @Before
    fun setUp() {
        validateTaskTimes = ValidateTaskTimes()
    }

    @Test
    fun `when the task is not scheduled then the validation result is true`() {
        val isScheduled = false
        val hour = 3
        val minutes = 15
        val result = validateTaskTimes(isScheduled, hour, minutes)

        assertTrue(result.successful)
    }

    @Test
    fun `when the task is scheduled and the hour is null but the minutes not then the validation result is false`() {
        val isScheduled = true
        val hour = null
        val minutes = 15
        val result = validateTaskTimes(isScheduled, hour, minutes)

        assertFalse(result.successful)
    }


}