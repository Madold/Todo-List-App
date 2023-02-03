package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.FormatDate
import com.markusw.app.domain.usecases.FormatTime
import com.markusw.app.ui.view.screens.main.DrawerDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {


    val todoList = repository.getTodoList()
    val drawerDestinations = listOf(
        DrawerDestination.MainScreen,
        DrawerDestination.SettingsScreen
    )

    companion object {
        const val TAG = "MainViewModel"
    }

    fun onTodoDone(todo: Todo) {
        viewModelScope.launch {
            todo.isDone = !todo.isDone
            repository.saveTodo(todo)
        }
    }




}