@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.todoinfo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.domain.model.Todo
import com.markusw.app.ui.view.screens.todoinfo.composables.TodoInfoTopBar
import com.markusw.app.ui.viewmodel.TodoInfoViewModel

@Composable
fun TaskInfoScreen(
    navController: NavController,
    todoId: Int,
) {

    Scaffold(
        topBar = {
            TodoInfoTopBar(navController)
        },
    ) { padding ->
        Content(
            todoId = todoId,
            paddingValues = padding
        )
    }


}

@Composable
fun Content(
    paddingValues: PaddingValues,
    todoId: Int,
    viewModel: TodoInfoViewModel = hiltViewModel()
) {

    val task by viewModel.getTodoById(todoId).collectAsState(
        initial = Todo(
            id = 0,
            title = "",
            description = "",
            isDone = false,
            endDate = "",
            endHour = ""
        )
    )
    val isTaskDescriptionEmpty by remember {
        derivedStateOf {
            task.description.isEmpty()
        }
    }
    val isScheduled by remember {
        derivedStateOf {
            task.endDate.isNotEmpty() && task.endHour.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = task.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (!isTaskDescriptionEmpty) {
            Text(text = task.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isScheduled) {
            Text(text = "Scheduled for ${task.endDate} at ${task.endHour}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}