@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.ui.viewmodel.WriteTodoViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

@Composable
fun Content(
    paddingValues: PaddingValues,
    viewModel: WriteTodoViewModel = hiltViewModel()
) {
    val todoTitle by viewModel.taskTitle.collectAsState()
    val todoDescription by viewModel.taskDescription.collectAsState()
    val isReminderChecked by viewModel.isReminderChecked.collectAsState()
    val notificationDeniedDialog by viewModel.notificationDeniedDialog.collectAsState()
    val calendarState = rememberSheetState()
    val clockState = rememberSheetState()

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
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Title"
        )
        Spacer(modifier = Modifier.height(8.dp))
        TaskTitleField(
            value = todoTitle,
            onValueChange = viewModel::onTaskTitleChanged,
            placeholderText = "Ex: Study in the morning"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description"
        )
        Spacer(modifier = Modifier.height(8.dp))
        TaskDescriptionField(
            value = todoDescription,
            onValueChange = viewModel::onTaskDescriptionChanged,
            placeholderText = "Ex: Study for 2 hours using the book"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Remember"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Switch(
            checked = isReminderChecked,
            onCheckedChange = viewModel::onReminderChecked
        )
        AnimatedVisibility(visible = isReminderChecked) {
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