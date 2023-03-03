package com.markusw.app.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.markusw.app.core.Constants
import com.markusw.app.domain.model.NotificationItem
import java.util.*
import javax.inject.Inject
import kotlin.time.seconds

class AndroidNotificationScheduler(
    private val context: Context
): NotificationScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: NotificationItem) {

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(Constants.TODO_TITLE, item.title)
            putExtra(Constants.TODO_DESCRIPTION, item.contentText)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().timeInMillis + item.delay,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(item: NotificationItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, NotificationReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}