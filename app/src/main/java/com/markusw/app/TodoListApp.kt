package com.markusw.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import com.markusw.app.core.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        val todoNotificationChannel = NotificationChannel(
            Constants.TODO_NOTIFICATION_CHANNEL_ID,
            Constants.TODO_NOTIFICATION_NAME,
            NotificationManager.IMPORTANCE_HIGH,
        )
        val notificationSound = Uri.parse("android.resource://${this.packageName}/${R.raw.doorbell}")
        val soundAttributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
        todoNotificationChannel.enableVibration(true)
        todoNotificationChannel.setSound(notificationSound, soundAttributes)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(todoNotificationChannel)
    }
}