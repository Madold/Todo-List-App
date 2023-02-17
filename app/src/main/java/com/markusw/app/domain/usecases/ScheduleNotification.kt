package com.markusw.app.domain.usecases

import android.content.Context
import androidx.work.Data
import com.markusw.app.domain.AndroidNotificationScheduler
import com.markusw.app.domain.NotificationSchedulerWorker
import com.markusw.app.domain.model.NotificationItem
import javax.inject.Inject

class ScheduleNotification @Inject constructor() {

    /*

    Since a user told me that notifications was not showing in his device until he opens the app
    I decided to use AlarmManager instead of WorkManager.

    This is the old code:

    operator fun invoke(context: Context, delay: Long, data: Data) {
        NotificationSchedulerWorker.scheduleNotification(
            context = context,
            delay = delay,
            data = data
        )
    }


   */

    operator fun invoke(context: Context, data: NotificationItem) {
        val notificationScheduler = AndroidNotificationScheduler(context)
        notificationScheduler.schedule(data)
    }

}



