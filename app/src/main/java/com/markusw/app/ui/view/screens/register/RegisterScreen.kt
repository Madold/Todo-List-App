@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.register

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.ui.view.screens.register.composables.RegisterScreenContent
import com.markusw.app.ui.view.screens.register.composables.RegisterScreenTopBar
import com.markusw.app.ui.viewmodel.RegisterScreenViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RegisterScreenTopBar(
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
        },
        content = { padding ->
            RegisterScreenContent(
                paddingValues = padding,
                emailValue = uiState.email,
                onEmailChange = viewModel::onEmailChanged,
                passwordValue = uiState.password,
                onPasswordChange = viewModel::onPasswordChanged,
                repeatedPasswordValue = uiState.repeatedPassword,
                onRepeatedPasswordChange = viewModel::onRepeatedPasswordChanged)
        }
    )
}