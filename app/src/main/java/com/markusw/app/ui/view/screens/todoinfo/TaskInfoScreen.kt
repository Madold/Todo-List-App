@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.todoinfo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.R
import com.markusw.app.domain.model.Todo
import com.markusw.app.ui.view.screens.todoinfo.composables.Content
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

