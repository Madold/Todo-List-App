package com.markusw.app.domain.usecases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class ValidateTaskDateTest {

    private lateinit var validateTaskDate: ValidateTaskDate

    @Before
    fun setUp() {
        validateTaskDate = ValidateTaskDate()
    }

    @Test
    fun `when the task is not scheduled then the validation result is true`() {

        val isScheduled = false
        val date = null
        val validationResult = validateTaskDate(isScheduled, date)

        assertTrue(validationResult.successful)
    }

    @Test
    fun `when the task is scheduled and the date is null the the validation result is false`() {
        val isScheduled = true
        val date = null
        val validationResult = validateTaskDate(isScheduled, date)

        assertFalse(validationResult.successful)
    }

    @Test
    fun `when the task is scheduled and the date is NOT null then the validation result is true`() {
        val isScheduled = true
        val date = LocalDate.now()
        val validationResult = validateTaskDate(isScheduled, date)

        assertTrue(validationResult.successful)
    }

}