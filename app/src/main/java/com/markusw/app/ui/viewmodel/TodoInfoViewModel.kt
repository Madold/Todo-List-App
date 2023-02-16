package com.markusw.app.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.domain.ValidationEvent
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.GetTodoById
import com.markusw.app.domain.usecases.SaveTask
import com.markusw.app.domain.usecases.ValidateTaskDescription
import com.markusw.app.domain.usecases.ValidateTaskTitle
import com.markusw.app.ui.view.screens.writtetodo.InputsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoInfoViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoById,
    private val saveTask: SaveTask,
    private val validateTaskTitle: ValidateTaskTitle,
    private val validateTaskDescription: ValidateTaskDescription,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _inputsState = MutableStateFlow(InputsState())
    val inputsState = _inputsState.asStateFlow()
    private var _selectedTask = MutableStateFlow(Todo())
    val selectedTask = _selectedTask.asStateFlow()
    private val validationChannel = Channel<ValidationEvent>()
    val validationEventChannel = validationChannel.receiveAsFlow()

    init {
        savedStateHandle.get<Int>("id")?.also {
            viewModelScope.launch {
                _selectedTask.value = getTodoByIdUseCase(it)
                _inputsState.value = _inputsState.value.copy(
                    taskTitle = _selectedTask.value.title,
                    taskDescription = _selectedTask.value.description
                )
            }
        }
    }

    fun onTitleChanged(title: String) {
        _inputsState.value = _inputsState.value.copy(taskTitle = title)
    }

    fun onDescriptionChanged(description: String) {
        _inputsState.value = _inputsState.value.copy(taskDescription = description)
    }

    fun onSaveChanges() {
        validateInputs()
    }

    private fun validateInputs() {
        val titleResult = validateTaskTitle(_inputsState.value.taskTitle)
        val descriptionResult = validateTaskDescription(_inputsState.value.taskDescription)
        val isAnyError = listOf(
            titleResult,
            descriptionResult
        ).any { !it.successful  }

        if (isAnyError) {
            _inputsState.value = _inputsState.value.copy(
                taskTitleError = titleResult.errorMessage,
                taskDescriptionError = descriptionResult.errorMessage
            )
            return
        }

        handleValidationSuccess()
    }

    private fun handleValidationSuccess() {
        viewModelScope.launch {
            validationChannel.send(ValidationEvent.Success)
            saveTask(
                _selectedTask.value.copy(
                    title = _inputsState.value.taskTitle,
                    description = _inputsState.value.taskDescription
                )
            )
        }
    }

}