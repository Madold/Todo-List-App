package com.markusw.app.domain.usecases

import com.markusw.app.domain.AndroidNotificationScheduler
import com.markusw.app.domain.model.NotificationItem
import javax.inject.Inject

class ScheduleNotification @Inject constructor(
        private val notificationScheduler: AndroidNotificationScheduler
) {
    operator fun invoke(data: NotificationItem) {
        notificationScheduler.schedule(data)
    }

}



