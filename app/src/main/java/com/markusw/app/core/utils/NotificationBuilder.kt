package com.markusw.app.core.utils

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.markusw.app.R
import com.markusw.app.core.Constants
import com.markusw.app.ui.view.activities.main.MainActivity

object NotificationBuilder {
    fun createNotification(
        context: Context,
        chanelId: String,
        contentText: String,
        title: String,
        @DrawableRes smallIcon: Int = R.drawable.app_logo
    ): Notification {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 123, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(context, chanelId)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(smallIcon)
            .setVibrate(Constants.DEFAULT_VIBRATION_PATTERN)
            .setContentIntent(pendingIntent)
            .build()
    }
}