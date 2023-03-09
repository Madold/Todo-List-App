package com.markusw.app

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class CloudMessagingService: FirebaseMessagingService()