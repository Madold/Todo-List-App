package com.markusw.app.data.network.services

import com.markusw.app.core.FirebaseClient
import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User
import javax.inject.Inject


abstract class SignInMethod @Inject constructor(): AuthService {

    @Inject
    protected lateinit var firebaseClient: FirebaseClient

    abstract override suspend fun authenticate(user: User): AuthenticationResult
}