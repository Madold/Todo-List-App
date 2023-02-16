@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.todoinfo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.domain.ValidationEvent
import com.markusw.app.ui.view.screens.todoinfo.composables.Content
import com.markusw.app.ui.view.screens.todoinfo.composables.SaveChangesFab
import com.markusw.app.ui.view.screens.todoinfo.composables.TodoInfoTopBar
import com.markusw.app.ui.viewmodel.TodoInfoViewModel

@Composable
fun TaskInfoScreen(
    navController: NavController,
    viewModel: TodoInfoViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = focusManager) {
        viewModel.validationEventChannel.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TodoInfoTopBar(navController)
        },
        floatingActionButton = {
            SaveChangesFab(
                onClick = {
                    viewModel.onSaveChanges()
                    focusManager.clearFocus()
                }
            )
        }
    ) { padding ->
        Content(
            paddingValues = padding
        )
    }
}

