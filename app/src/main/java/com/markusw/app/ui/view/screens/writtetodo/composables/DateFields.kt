package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.R
import com.markusw.app.ui.viewmodel.WriteTodoViewModel
import com.maxkeppeker.sheets.core.models.base.SheetState

@Composable
fun DateFields(
    calendarState: SheetState,
    clockState: SheetState,
    viewModel: WriteTodoViewModel = hiltViewModel(),
) {

    val inputsState by viewModel.inputsState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TaskDateField(
            value = if (inputsState.endDate != null) viewModel.getFormattedDate() else "",
            onClick = {
                calendarState.show()
            },
            placeholder = {
                Text(stringResource(id = R.string.pick_date_text))
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            },
            isError = inputsState.endDateError != null,
            errorMessage = inputsState.endDateError?.asString()
        )
        TaskHourField(
            value = if (inputsState.endHour != null && inputsState.endMinute != null) viewModel.getFormattedTime() else "",
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
                Text(stringResource(id = R.string.pick_time_text))
            },
            isError = inputsState.timeInputError != null,
            errorMessage = inputsState.timeInputError?.asString()
        )
    }
}