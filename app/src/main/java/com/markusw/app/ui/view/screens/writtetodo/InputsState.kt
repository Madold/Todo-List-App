package com.markusw.app.ui.view.screens.writtetodo

import java.time.LocalDate

data class InputsState(
    val taskTitle: String = "",
    val taskTitleError: String? = null,
    val taskDescription: String = "",
    val taskDescriptionError: String? = null,
    val isScheduled: Boolean = false,
    val endDate: LocalDate? = null,
    val endDateError: String? = null,
    val endHour: Int? = null,
    val endMinute: Int? = null,
    val timeInputError : String? = null,
)