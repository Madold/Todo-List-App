package com.markusw.app.core

import androidx.compose.runtime.staticCompositionLocalOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseClient @Inject constructor() {
    val auth get() = Firebase.auth
    val analytics = Firebase.analytics
    val crashlytics = Firebase.crashlytics
    val currentUser get() = auth.currentUser
}

val LocalFirebaseClient = staticCompositionLocalOf { FirebaseClient() }
