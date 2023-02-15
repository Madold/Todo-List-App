@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.ui.viewmodel.WriteTodoViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.markusw.app.R

@Composable
fun Content(
    paddingValues: PaddingValues,
    viewModel: WriteTodoViewModel = hiltViewModel()
) {
    val inputsState by viewModel.inputsState.collectAsState()
    val notificationDeniedDialog by viewModel.notificationDeniedDialog.collectAsState()
    val calendarState = rememberSheetState()
    val clockState = rememberSheetState()
    val scrollState = rememberScrollState()

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            viewModel.onTaskEndDateChanged(date)
        }
    )
    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            viewModel.onTaskEndTimeChanged(hours, minutes)
        },
        config = ClockConfig(
            is24HourFormat = true,
        )
    )

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TaskTitleField(
            value = inputsState.taskTitle,
            onValueChange = viewModel::onTaskTitleChanged,
            placeholderText = stringResource(id = R.string.task_title_placeholder),
            isError = inputsState.taskTitleError != null,
            errorMessage = inputsState.taskTitleError?.asString()
        )
        TaskDescriptionField(
            value = inputsState.taskDescription,
            onValueChange = viewModel::onTaskDescriptionChanged,
            placeholderText = stringResource(id = R.string.task_description_placeholder),
            isError = inputsState.taskDescriptionError != null,
            errorMessage = inputsState.taskDescriptionError?.asString()
        )
        ScheduleTaskSwitch(
            checked = inputsState.isScheduled,
            onCheckedChange = viewModel::onReminderChecked
        )
        AnimatedVisibility(visible = inputsState.isScheduled) {
            DateFields(
                calendarState = calendarState,
                clockState = clockState
            )
        }

        if (notificationDeniedDialog) {
            NotificationDeniedDialog(
                onConfirm = viewModel::onNotificationDeniedDialogDismissed,
            )
        }

    }
}