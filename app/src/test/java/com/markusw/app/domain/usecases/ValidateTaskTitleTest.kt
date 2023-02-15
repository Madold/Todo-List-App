package com.markusw.app.domain.usecases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class ValidateTaskTitleTest @Inject constructor() {

    private lateinit var validateTaskTitle: ValidateTaskTitle

    @Before
    fun setUp() {
        validateTaskTitle = ValidateTaskTitle()
    }

    @Test
    fun `when the title is an empty string then the validation result is false`() {
        val title = ""
        val result = validateTaskTitle(title)

        assertFalse(result.successful)
    }

    @Test
    fun `when the title is a whitespace string then the validation result is false`() {
        val title = " "
        val result = validateTaskTitle(title)

        assertFalse(result.successful)
    }

    @Test
    fun `when the title is an empty string line of whitespaces then the validation result is false`() {
        val title = "                                        "
        val result = validateTaskTitle(title)

        assertFalse(result.successful)
    }


}