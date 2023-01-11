package com.markusw.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import com.markusw.app.ui.view.screens.main.BottomBarScreen.HomeScreen
import com.markusw.app.ui.view.screens.main.BottomBarScreen.WriteTodoScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _todoList = MutableStateFlow(listOf<Todo>())
    val todosList = _todoList.asStateFlow()
    private var _todoTitle = MutableStateFlow("")
    val todoTitle = _todoTitle.asStateFlow()
    private var _todoDescription = MutableStateFlow("")
    val todoDescription = _todoDescription.asStateFlow()
    val bottomBarScreens = listOf(HomeScreen, WriteTodoScreen)
    private val TAG = "viewmodel"

    init {
        updateTodoList()
    }

    private fun updateTodoList() {
        viewModelScope.launch {
            val response = repository.getTodoList()
            _todoList.value = response

            Log.d(TAG, "updateTodoList: $response")
        }
    }

    fun onTodoTitleChanged(title: String) {
        _todoTitle.value = title
    }

    fun onTodoDescriptionChanged(description: String) {
        _todoDescription.value = description
    }

    fun onTodoDone(todo: Todo) {
        viewModelScope.launch {
            todo.isDone = !todo.isDone
            Log.d(TAG, "onTodoDone: Todo id = ${todo.id} ")
            repository.saveTodo(todo)
        }
        updateTodoList()
    }

    fun onTodoDeleted(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
            updateTodoList()
        }
    }

    fun saveTodo() {
        viewModelScope.launch {
            repository.saveTodo(
                Todo(
                    title = _todoTitle.value,
                    description = _todoDescription.value
                )
            )
        }
        updateTodoList()
        resetFields()
    }

    private fun resetFields() {
        _todoTitle.value = ""
        _todoDescription.value = ""
    }

    private fun deleteAllTodos() {
        viewModelScope.launch {
            repository.deleteAllTodo()
        }
    }

}