@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.writtetodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.writtetodo.composables.*
import com.markusw.app.ui.viewmodel.MainViewModel
import com.markusw.app.ui.viewmodel.WriteTodoViewModel
import kotlinx.coroutines.launch

@Composable
fun WriteTodoScreen(
    navController: NavController,
    viewModel: WriteTodoViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { }
            )
        },
        bottomBar = {
            BottomButton(
                snackbarHostState = snackbarHostState,
                onClick = {
                    viewModel.saveTask()
                    focusManager.clearFocus()
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Task created",
                            actionLabel = "Ok",
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Content(paddingValues = padding)
    }
}











