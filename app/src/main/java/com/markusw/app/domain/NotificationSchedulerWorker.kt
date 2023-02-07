package com.markusw.app.domain

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.work.*
import com.markusw.app.R
import com.markusw.app.core.Constants
import com.markusw.app.core.utils.NotificationBuilder
import java.util.concurrent.TimeUnit

class NotificationSchedulerWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(
    appContext = context,
    params = params
) {

    companion object {
        fun scheduleNotification(
            context: Context,
            delay: Long,
            data: Data
        ) {
            val notificationWork =
                OneTimeWorkRequest.Builder(NotificationSchedulerWorker::class.java)
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .setInputData(data)
                    .build()
            val workManagerInstance = WorkManager.getInstance(context)
            workManagerInstance.enqueue(notificationWork)
        }
    }

    override suspend fun doWork(): Result {
        val notification = NotificationBuilder.createNotification(
            context = context,
            title = inputData.getString(Constants.TODO_TITLE) ?: "",
            chanelId = Constants.TODO_NOTIFICATION_CHANNEL_ID,
            contentText = inputData.getString(Constants.TODO_DESCRIPTION) ?: "",
        )
        showNotification(notification)

        return Result.success()
    }

    private fun showNotification(notification: Notification) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

}