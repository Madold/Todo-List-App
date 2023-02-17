package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.GetTodoList
import com.markusw.app.domain.usecases.PerformOnTodoDeleted
import com.markusw.app.domain.usecases.PerformOnTodoDone
import com.markusw.app.ui.view.UiState
import com.markusw.app.ui.view.UiState.Loading
import com.markusw.app.ui.view.UiState.Rest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val performOnTodoDone: PerformOnTodoDone,
    private val performOnTodoDeleted: PerformOnTodoDeleted,
    getTodoList: GetTodoList
) : ViewModel() {

    val todoList = getTodoList()
    private var _uiState = MutableStateFlow<UiState>(Rest)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = Loading
            delay(3000)
            _uiState.value = Rest
        }
    }

    fun onTodoDone(todo: Todo) {
        viewModelScope.launch { performOnTodoDone(todo) }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch { performOnTodoDeleted(todo) }
    }

}