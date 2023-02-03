package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.ui.viewmodel.WriteTodoViewModel
import com.maxkeppeker.sheets.core.models.base.SheetState

@Composable
fun DateFields(
    calendarState: SheetState,
    clockState: SheetState,
    viewModel: WriteTodoViewModel = hiltViewModel(),
) {

    val todoEndDate by viewModel.taskEndDate.collectAsState()
    val todoEndMinutes by viewModel.taskEndMinutes.collectAsState()
    val todoEndHour by viewModel.taskEndHour.collectAsState()

    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Remember me"
        )
        Spacer(modifier = Modifier.height(8.dp))
        TaskDateField(
            value = if (todoEndDate != null) viewModel.getFormattedDate() else "",
            onClick = {
                calendarState.show()
            },
            placeholder = {
                Text("Pick a date")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "At"
        )
        Spacer(modifier = Modifier.height(8.dp))
        TaskHourField(
            value = if (todoEndHour != null && todoEndMinutes != null) viewModel.getFormattedTime() else "",
            onClick = {
                clockState.show()
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null
                )
            },
            placeholder = {
                Text("Pick a time")
            }
        )
    }
}