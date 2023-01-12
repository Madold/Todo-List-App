package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoInfoViewModel: ViewModel() {

    fun getTodoById(id: Int) {
        viewModelScope.launch {

        }
    }

}