package com.markusw.app.core.utils

import android.app.Notification
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.markusw.app.R

object NotificationBuilder {
    fun createNotification(
        context: Context,
        chanelId: String,
        contentText: String,
        title: String,
        @DrawableRes smallIcon: Int = R.drawable.app_icon
    ): Notification {
        return NotificationCompat.Builder(context, chanelId)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(smallIcon)
            .build()
    }
}