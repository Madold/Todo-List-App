package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
        ) {

            val todoList by viewModel.todosList.collectAsState()
            val tasksText by remember {
                derivedStateOf {
                    if (todoList.isEmpty()) "Nada por aquí ;)" else "Estas son tus tareas pendientes :)"
                }
            }

            Text(
                text = tasksText,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(todoList) { todo ->
                        TodoItem(
                            todo = todo,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}




