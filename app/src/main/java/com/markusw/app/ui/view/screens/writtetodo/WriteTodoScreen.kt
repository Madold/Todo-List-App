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
import com.markusw.app.R
import com.markusw.app.domain.ValidationEvent
import com.markusw.app.ui.view.screens.writtetodo.composables.*
import com.markusw.app.ui.viewmodel.WriteTodoViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WriteTodoScreen(
    navController: NavController,
    viewModel: WriteTodoViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val permissionState = rememberPermissionState(permission = POST_NOTIFICATIONS)
    val context = LocalContext.current

    if (permissionState.status.shouldShowRationale) {
        viewModel.onNotificationPermissionDenied()
    }

    LaunchedEffect(key1 = context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    viewModel.clearInputs()
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.task_created_text),
                        actionLabel = context.getString(R.string.ok),
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
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
                    val isAndroidVersionMayorOrEqualsToTiramisu =
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

                    // For android versions below Tiramisu
                    if (!isAndroidVersionMayorOrEqualsToTiramisu) {
                        viewModel.saveTask(context)
                        focusManager.clearFocus()
                        return@BottomButton
                    }

                    // For android versions Tiramisu or above
                    permissionState.launchPermissionRequest()

                    if (!permissionState.status.isGranted && !permissionState.status.shouldShowRationale) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.notification_permission_denied),
                            Toast.LENGTH_SHORT
                        ).show()
                        focusManager.clearFocus()
                        return@BottomButton
                    }

                    viewModel.saveTask(context)
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











