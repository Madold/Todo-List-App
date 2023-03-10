package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.data.network.services.FirebaseSignInMethod
import com.markusw.app.data.network.services.GoogleSignInMethod
import com.markusw.app.domain.AuthenticationEvent
import com.markusw.app.domain.model.User
import com.markusw.app.domain.usecases.UserAuthenticator
import com.markusw.app.domain.usecases.ValidateEmail
import com.markusw.app.domain.usecases.ValidatePassword
import com.markusw.app.ui.view.screens.presentation.PresentationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresentationViewModel @Inject constructor(
    private val userAuthenticator: UserAuthenticator,
    private val firebaseSignInMethod: FirebaseSignInMethod,
    private val googleSignInMethod: GoogleSignInMethod,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private var _uiState = MutableStateFlow(PresentationState())
    val uiState = _uiState.asStateFlow()
    private val channel = Channel<AuthenticationEvent>()
    val authenticationEventChannel = channel.receiveAsFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onLogin() {
        viewModelScope.launch {
            val emailResult = validateEmail(_uiState.value.email)
            val passwordResult = validatePassword(_uiState.value.password)
            val isAnyError = listOf(emailResult, passwordResult).any { !it.successful }

            if (isAnyError) {
                _uiState.update {
                    it.copy(
                        emailError = emailResult.errorMessage,
                        passwordError = passwordResult.errorMessage
                    )
                }
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = true,
                    passwordError = null,
                    emailError = null
                )
            }

            val result = userAuthenticator.authenticate(
                user = User(
                    email = _uiState.value.email,
                    password = _uiState.value.password
                ),
                method = firebaseSignInMethod
            )

            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }

            if(!result.success) {
                channel.send(AuthenticationEvent.Failure(
                    reason = result.errorMessage ?: "Unknown error, try again later"
                ))
                return@launch
            }

            channel.send(AuthenticationEvent.Success)
        }
    }

}