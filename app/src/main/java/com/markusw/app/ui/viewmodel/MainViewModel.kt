package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.GetTodoList
import com.markusw.app.domain.usecases.PerformOnTodoDeleted
import com.markusw.app.domain.usecases.PerformOnTodoDone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val performOnTodoDone: PerformOnTodoDone,
    private val performOnTodoDeleted: PerformOnTodoDeleted,
    getTodoList: GetTodoList
) : ViewModel() {

    val todoList = getTodoList()

    fun onTodoDone(todo: Todo) {
        viewModelScope.launch { performOnTodoDone(todo) }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch { performOnTodoDeleted(todo) }
    }

}