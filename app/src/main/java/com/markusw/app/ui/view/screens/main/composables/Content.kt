package com.markusw.app.ui.view.screens.main.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.ui.viewmodel.MainViewModel

@Composable
fun Content(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val taskList by viewModel.todoList.collectAsState(initial = emptyList())
    val isTaskListEmpty by remember { derivedStateOf { taskList.isEmpty() } }
    val taskCounterText by remember {
        derivedStateOf {
            if (taskList.isNotEmpty()) taskList.size.toString() else ""
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("My tasks ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(taskCounterText)
                }
            },
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(taskList) { task ->
                    TaskItem(
                        task = task,
                        navController = navController
                    )
                    Log.d(MainViewModel.TAG, "Task list updated")
                }
            }
            if (isTaskListEmpty) {
                EmptyListAnimation()
            }
        }
    }
}