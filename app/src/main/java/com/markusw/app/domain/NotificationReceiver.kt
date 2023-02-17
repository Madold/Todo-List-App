package com.markusw.app.domain

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.markusw.app.core.Constants
import com.markusw.app.core.utils.NotificationBuilder

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationTitle = intent?.getStringExtra(Constants.TODO_TITLE) ?: return
        val notificationDescription = intent.getStringExtra(Constants.TODO_DESCRIPTION) ?: return
        val notification = NotificationBuilder.createNotification(
            context = context!!,
            title = notificationTitle,
            chanelId = Constants.TODO_NOTIFICATION_CHANNEL_ID,
            contentText = notificationDescription,
        )
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }

}