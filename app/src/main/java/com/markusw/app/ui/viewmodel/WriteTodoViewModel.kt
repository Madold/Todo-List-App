package com.markusw.app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import com.markusw.app.R
import com.markusw.app.core.Constants
import com.markusw.app.core.utils.DelayComputer
import com.markusw.app.domain.ValidationEvent
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.usecases.*
import com.markusw.app.ui.view.screens.writtetodo.InputsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WriteTodoViewModel @Inject constructor(
    private val formatDate: FormatDate,
    private val formatTime: FormatTime,
    private val scheduleNotification: ScheduleNotification,
    private val saveTask: SaveTask,
    private val validateTaskTitle: ValidateTaskTitle,
    private val validateTaskDescription: ValidateTaskDescription,
    private val validateTaskDate: ValidateTaskDate,
    private val validateTaskTime: ValidateTaskTimes
) : ViewModel() {

    private var _inputsState = MutableStateFlow(InputsState())
    val inputsState = _inputsState.asStateFlow()
    private var _notificationDeniedDialog = MutableStateFlow(false)
    val notificationDeniedDialog = _notificationDeniedDialog.asStateFlow()
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    fun onTaskTitleChanged(title: String) {
        _inputsState.value = _inputsState.value.copy(taskTitle = title)
    }

    fun onTaskDescriptionChanged(description: String) {
        _inputsState.value = _inputsState.value.copy(taskDescription = description)
    }

    fun onTaskEndDateChanged(date: LocalDate) {
        _inputsState.value = _inputsState.value.copy(endDate = date)
    }

    fun getFormattedDate(): String {
        return formatDate(_inputsState.value.endDate!!)
    }

    fun onTaskEndTimeChanged(hours: Int, minutes: Int) {
        _inputsState.value = _inputsState.value.copy(
            endHour = hours,
            endMinute = minutes
        )
    }

    fun getFormattedTime(): String {
        return formatTime(
            hours = _inputsState.value.endHour!!,
            minutes = _inputsState.value.endMinute!!
        )
    }

    fun saveTask(
        context: Context
    ) {
        val titleResult = validateTaskTitle(_inputsState.value.taskTitle)
        val descriptionResult = validateTaskDescription(_inputsState.value.taskDescription)
        val dateResult = validateTaskDate(
            scheduled = _inputsState.value.isScheduled,
            date = _inputsState.value.endDate
        )
        val timeResult = validateTaskTime(
            scheduled = _inputsState.value.isScheduled,
            hour = _inputsState.value.endHour,
            minutes = _inputsState.value.endMinute
        )
        val isAnyError = listOf(
            titleResult,
            descriptionResult,
            dateResult,
            timeResult
        ).any { !it.successful }


        if(isAnyError) {
            _inputsState.value = _inputsState.value.copy(
                taskTitleError = titleResult.errorMessage,
                taskDescriptionError = descriptionResult.errorMessage,
                endDateError = dateResult.errorMessage,
                timeInputError = timeResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            saveTask(
                Todo(
                    title = _inputsState.value.taskTitle,
                    description = _inputsState.value.taskDescription,
                    endDate = _inputsState.value.endDate?.let { getFormattedDate() } ?: "",
                    endHour = _inputsState.value.endHour?.let { getFormattedTime() } ?: ""
                )
            )

            if(_inputsState.value.isScheduled) {
                scheduleTaskNotification(context = context)
            }

            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun clearInputs() {
        _inputsState.value = InputsState()
    }

    fun onReminderChecked(isChecked: Boolean) {
        _inputsState.value = _inputsState.value.copy(isScheduled = isChecked)
    }

    fun onNotificationPermissionDenied() {
        _notificationDeniedDialog.value = true
    }

    fun onNotificationDeniedDialogDismissed() {
        _notificationDeniedDialog.value = false
    }

    private fun scheduleTaskNotification(context: Context) {
        viewModelScope.launch {
            val data = buildData(context)
            val delay = computeDelay()
            scheduleNotification(context, delay, data)
        }
    }

    private fun buildData(context: Context): Data {
        return Data.Builder()
            .apply {
                putString(Constants.TODO_TITLE, context.getString(R.string.reminder_text))
                putString(
                    Constants.TODO_DESCRIPTION,
                    "${context.getString(R.string.reminder_description_text)} ${_inputsState.value.taskTitle}!"
                )
            }
            .build()
    }

    private fun computeDelay(): Long {
        return DelayComputer.computeDelay(
            endDate = _inputsState.value.endDate!!,
            endHour = _inputsState.value.endHour!!,
            endMinutes = _inputsState.value.endMinute!!
        )
    }

}


