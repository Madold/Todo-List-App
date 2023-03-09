package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.markusw.app.ui.view.screens.presentation.PresentationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PresentationViewModel @Inject constructor(): ViewModel() {

    private var _uiState = MutableStateFlow(PresentationState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

}