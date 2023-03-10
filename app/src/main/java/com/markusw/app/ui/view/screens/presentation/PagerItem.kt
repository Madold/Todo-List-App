package com.markusw.app.ui.view.screens.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.ui.view.screens.presentation.composables.FirstSlide
import com.markusw.app.ui.view.screens.presentation.composables.LoginSlide
import com.markusw.app.ui.view.screens.presentation.composables.SecondSlide
import com.markusw.app.ui.viewmodel.PresentationViewModel

sealed class PagerItem(
        val content: @Composable () -> Unit
) {
    object FirstSlide : PagerItem(
            content = {
                FirstSlide(
                        modifier = Modifier.fillMaxSize()
                )
            }
    )

    object SecondSlide: PagerItem(
            content = {
                SecondSlide(modifier = Modifier.fillMaxSize())
            }
    )

    object LoginSlide : PagerItem(
            content = {

                val viewModel: PresentationViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsState()

                LoginSlide(
                        modifier = Modifier.fillMaxSize(),
                        emailValue = uiState.email,
                        onEmailChange = viewModel::onEmailChanged,
                        passwordValue = uiState.password,
                        onPasswordChange = viewModel::onPasswordChanged,
                        onGoogleButtonClick = {},
                        onFaceBookButtonClick = {}
                )
            }
    )
}
