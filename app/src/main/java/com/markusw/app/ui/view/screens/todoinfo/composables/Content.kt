
package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.R
import com.markusw.app.ui.viewmodel.TodoInfoViewModel

@Composable
fun Content(
    paddingValues: PaddingValues,
    viewModel: TodoInfoViewModel = hiltViewModel()
) {

    val task by viewModel.selectedTask.collectAsState()
    val inputsState by viewModel.inputsState.collectAsState()
    val isScheduled by remember {
        derivedStateOf {
            task.endDate.isNotEmpty() && task.endHour.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        EditTitleTextField(
            value = inputsState.taskTitle,
            placeholder = stringResource(id = R.string.task_title_placeholder),
            singleLine = false,
            isError = inputsState.taskTitleError != null,
            errorMessage = inputsState.taskTitleError?.asString(),
            onValueChange = viewModel::onTitleChanged,
            fontSize = 22.sp
        )

        if (task.description.isNotEmpty()) {
            EditDescriptionField(
                value = inputsState.taskDescription,
                placeholder = stringResource(id = R.string.task_description_placeholder),
                isError = inputsState.taskDescriptionError != null,
                errorMessage = inputsState.taskDescriptionError?.asString(),
                onValueChange = viewModel::onDescriptionChanged,
                fontSize = 18.sp
            )
        }

        if (isScheduled) {
            Text(
                text = "${stringResource(id = R.string.scheduled_for_text)} ${task.endDate} ${
                    stringResource(
                        id = R.string.at_text
                    )
                } ${task.endHour}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}