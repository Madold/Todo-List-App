package com.markusw.app.ui.view.screens.writtetodo

import com.markusw.app.ui.view.UiText
import java.time.LocalDate

data class InputsState(
    val taskTitle: String = "",
    val taskTitleError: UiText? = null,
    val taskDescription: String = "",
    val taskDescriptionError: UiText? = null,
    val isScheduled: Boolean = false,
    val endDate: LocalDate? = null,
    val endDateError: UiText? = null,
    val endHour: Int? = null,
    val endMinute: Int? = null,
    val timeInputError : UiText? = null,
)