package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoInfoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getTodoById(id: Int): Flow<Todo> {
        return repository.getTodoById(id)
    }

}