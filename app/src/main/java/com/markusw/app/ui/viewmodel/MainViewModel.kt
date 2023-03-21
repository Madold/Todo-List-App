package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.core.FirebaseClient
import com.markusw.app.data.Repository
import com.markusw.app.data.network.services.FirebaseSignInMethod
import com.markusw.app.data.network.services.GoogleSignInMethod
import com.markusw.app.domain.AuthenticationEvent
import com.markusw.app.domain.SignOutEvent
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.model.User
import com.markusw.app.domain.model.UserSettings
import com.markusw.app.domain.usecases.*
import com.markusw.app.ui.view.screens.main.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val performOnTodoDone: PerformOnTodoDone,
    private val performOnTodoDeleted: PerformOnTodoDeleted,
    private val userAuthenticator: UserAuthenticator,
    private val firebaseSignInMethod: FirebaseSignInMethod,
    private val googleSignInMethod: GoogleSignInMethod,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val repository: Repository,
    getTodoList: GetTodoList,
) : ViewModel() {

    @Inject
    lateinit var firebaseClient: FirebaseClient
    val todoList = getTodoList()
    private var _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()
    private val channel = Channel<AuthenticationEvent>()
    private val logOutChannel = Channel<SignOutEvent>()
    val logOutEventChannel = logOutChannel.receiveAsFlow()
    val authenticationEventChannel = channel.receiveAsFlow()
    val settings = repository.getSettings()


    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(3000)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onTodoDone(todo: Todo) {
        viewModelScope.launch { performOnTodoDone(todo) }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch { performOnTodoDeleted(todo) }
    }

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

    fun onGoogleSignInResult(token: Any?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val authResult = userAuthenticator.authenticate(
                user = User(token = token),
                method = googleSignInMethod
            )

            _uiState.update { it.copy(isLoading = false) }

            if(!authResult.success) {
                channel.send(AuthenticationEvent.Failure(
                    reason = authResult.errorMessage ?: "Unknown error, try again later"
                ))
                firebaseClient.crashlytics.log("Google sign in failed. Reason: ${authResult.errorMessage}")
                return@launch
            }

            channel.send(AuthenticationEvent.Success)
            _uiState.update { MainScreenState() }
        }
    }

    fun saveSettings(settings: UserSettings) {
        viewModelScope.launch {
            repository.saveSettings(settings)
        }
    }

    fun onSignOut() {
        viewModelScope.launch {
            firebaseClient.auth.signOut()
            logOutChannel.send(SignOutEvent.Success)
        }
    }

}