@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)

package com.markusw.app.ui.view.screens.writtetodo

import android.Manifest.permission.POST_NOTIFICATIONS
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.markusw.app.ui.view.screens.writtetodo.composables.*
import com.markusw.app.ui.viewmodel.WriteTodoViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WriteTodoScreen(
    navController: NavController,
    viewModel: WriteTodoViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val isReminderChecked by viewModel.isReminderChecked.collectAsState()
    val permissionState = rememberPermissionState(
        permission = POST_NOTIFICATIONS
    )
    val context = LocalContext.current

    if (permissionState.status.shouldShowRationale) {
        viewModel.onNotificationPermissionDenied()
    }

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
                onClick = {

                    val isAndroidVersionMayorOrEqualsToTiramisu = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

                    if (!isAndroidVersionMayorOrEqualsToTiramisu) {
                        if (isReminderChecked) {
                            viewModel.scheduleTaskNotification(context)
                        }
                        viewModel.saveTask()
                        coroutineScope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                message = "Task created",
                                actionLabel = "Ok",
                                duration = SnackbarDuration.Short
                            )
                        }

                        focusManager.clearFocus()
                        return@BottomButton
                    }

                    if (!isReminderChecked) {
                        viewModel.saveTask()
                        coroutineScope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                message = "Task created",
                                actionLabel = "Ok",
                                duration = SnackbarDuration.Short
                            )
                        }
                        focusManager.clearFocus()
                        return@BottomButton
                    }

                    permissionState.launchPermissionRequest()

                    if (!permissionState.status.isGranted) {
                        Toast.makeText(
                            context,
                            "Notification permission denied, enable it in settings",
                            Toast.LENGTH_SHORT
                        ).show()
                        focusManager.clearFocus()
                        return@BottomButton
                    }

                    viewModel.saveTask()
                    viewModel.scheduleTaskNotification(context)
                    coroutineScope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = "Task created",
                            actionLabel = "Ok",
                            duration = SnackbarDuration.Short
                        )
                    }
                    focusManager.clearFocus()
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











