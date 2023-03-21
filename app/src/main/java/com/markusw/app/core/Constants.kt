package com.markusw.app.core

import com.google.android.gms.auth.api.signin.GoogleSignInOptions

object Constants {
    const val TODOS_TABLE = "todos_table"
    const val TODO_NOTIFICATION_CHANNEL_ID = "todo_notification_channel_id"
    const val TODO_NOTIFICATION_NAME = "Todo Notification Channel"
    const val TODO_TITLE = "todo_title"
    const val TODO_DESCRIPTION = "todo_description"
    const val USER_SETTINGS_TABLE = "user_settings_table"
    val DEFAULT_VIBRATION_PATTERN = longArrayOf(1000, 500, 1000, 500, 1000)
}