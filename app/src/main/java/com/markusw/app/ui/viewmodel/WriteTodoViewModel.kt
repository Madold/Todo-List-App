package com.markusw.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.FormatDate
import com.markusw.app.domain.usecases.FormatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WriteTodoViewModel @Inject constructor(
    private val repository: Repository,
    private val formatDate: FormatDate,
    private val formatTime: FormatTime
) : ViewModel() {

    companion object {
        const val TAG = "WriteTodoViewModel"
    }

    private var _taskTitle = MutableStateFlow("")
    val taskTitle = _taskTitle.asStateFlow()
    private var _taskDescription = MutableStateFlow("")
    val taskDescription = _taskDescription.asStateFlow()
    private var _taskEndDate: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val taskEndDate = _taskEndDate.asStateFlow()
    private var _taskEndHour = MutableStateFlow<Int?>(null)
    val taskEndHour = _taskEndHour.asStateFlow()
    private var _taskEndMinutes = MutableStateFlow<Int?>(null)
    val taskEndMinutes = _taskEndMinutes.asStateFlow()
    private var _isReminderChecked = MutableStateFlow(false)
    val isReminderChecked = _isReminderChecked.asStateFlow()

    fun onTaskTitleChanged(title: String) {
        _taskTitle.value = title
    }

    fun onTaskDescriptionChanged(description: String) {
        _taskDescription.value = description
    }

    fun onTaskEndDateChanged(date: LocalDate) {
        _taskEndDate.value = date
    }

    fun getFormattedDate(): String {
        return formatDate(taskEndDate.value!!)
    }

    fun onTaskEndTimeChanged(hours: Int, minutes: Int) {
        _taskEndHour.value = hours
        _taskEndMinutes.value = minutes
    }

    fun getFormattedTime(): String {
        return formatTime(
            hours = taskEndHour.value!!,
            minutes = taskEndMinutes.value!!
        )
    }

    fun saveTask() {
        viewModelScope.launch {
            repository.saveTodo(
                Todo(
                    title = taskTitle.value,
                    description = taskDescription.value,
                )
            )
            clearInputs()
        }
    }

    private fun clearInputs() {
        _taskTitle.value = ""
        _taskDescription.value = ""
        _taskEndDate.value = null
        _taskEndHour.value = null
        _taskEndMinutes.value = null
    }

    fun onReminderChecked(isChecked: Boolean) {
        _isReminderChecked.value = isChecked
    }


}