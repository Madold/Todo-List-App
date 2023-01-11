@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun WriteTodoScreen(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
        ) {

            val todoTitle by viewModel.todoTitle.collectAsState()
            val todoDescription by viewModel.todoDescription.collectAsState()
            val coroutineScope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current

            Text(
                text = "Agregar una nueva tarea",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = todoTitle,
                onValueChange = viewModel::onTodoTitleChanged,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                label = {
                    Text("Titulo")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = todoDescription,
                onValueChange = viewModel::onTodoDescriptionChanged,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                label = {
                    Text("Descripción de la tarea")
                },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                viewModel.saveTodo()
                coroutineScope.launch {
                    keyboardController?.hide()
                    snackbarHostState.showSnackbar(
                        message = "Tarea agregada correctamente",
                        actionLabel = "Ok",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Agregar tarea")
            }
        }
    }
}