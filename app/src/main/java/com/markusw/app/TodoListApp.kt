package com.markusw.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.markusw.app.core.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoListApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val todoNotificationChannel = NotificationChannel(
            Constants.TODO_NOTIFICATION_CHANNEL_ID,
            Constants.TODO_NOTIFICATION_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(todoNotificationChannel)
    }
}