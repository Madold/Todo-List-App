package com.markusw.app.domain

import com.markusw.app.domain.model.NotificationItem

interface NotificationScheduler {
    fun schedule(item: NotificationItem)
    fun cancel(item: NotificationItem)
}