package com.markusw.app.domain.usecases

import android.content.Context
import androidx.work.Data
import com.markusw.app.domain.NotificationSchedulerWorker
import javax.inject.Inject

class ScheduleNotification @Inject constructor() {

    operator fun invoke(context: Context, delay: Long, data: Data) {
        NotificationSchedulerWorker.scheduleNotification(context, delay, data)
    }

}