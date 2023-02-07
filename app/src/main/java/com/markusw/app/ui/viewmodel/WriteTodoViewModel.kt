package com.markusw.app.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import com.markusw.app.core.Constants
import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.FormatDate
import com.markusw.app.domain.usecases.FormatTime
import com.markusw.app.domain.usecases.ScheduleNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import javax.inject.Inject

@HiltViewModel
class WriteTodoViewModel @Inject constructor(
    private val repository: Repository,
    private val formatDate: FormatDate,
    private val formatTime: FormatTime,
    private val scheduleNotification: ScheduleNotification
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
    private var _notificationDeniedDialog = MutableStateFlow(false)
    val notificationDeniedDialog = _notificationDeniedDialog.asStateFlow()

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
                    endDate = taskEndDate.value?.let {
                          getFormattedDate()
                    } ?: "",
                    endHour = taskEndHour.value?.let {
                        getFormattedTime()
                    } ?: ""
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

    fun onNotificationPermissionDenied() {
        _notificationDeniedDialog.value = true
    }

    fun onNotificationDeniedDialogDismissed() {
        _notificationDeniedDialog.value = false
    }

    fun scheduleTaskNotification(context: Context) {
        viewModelScope.launch {
            val data = buildData()
            val delay = computeDelay()
            Log.d(TAG, "Delay: $delay")
            scheduleNotification(context, delay, data)
        }
    }

    private fun buildData(): Data {
        return Data.Builder()
            .apply {
                putString(Constants.TODO_TITLE, "Reminder")
                putString(Constants.TODO_DESCRIPTION, "Hi, this is a reminder for your task: ${_taskTitle.value}!")
            }
            .build()
    }

    private fun computeDelay(): Long {
        val now = LocalDate.now()
        val dateDifference = now.until(_taskEndDate.value!!)
        val currentTime = LocalTime.now()
        val timeDifference = Duration.between(currentTime, LocalTime.of(_taskEndHour.value!!, _taskEndMinutes.value!!))

        return dateDifference.toMillis() + timeDifference.toMillis()
    }

}

fun Period.toMillis(): Long  {
    val days = this.days
    val months = this.months
    val years = this.years
    val millis = days * 24 * 60 * 60 * 1000 + months * 30 * 24 * 60 * 60 * 1000 + years * 365 * 24 * 60 * 60 * 1000

    return millis.toLong()
}