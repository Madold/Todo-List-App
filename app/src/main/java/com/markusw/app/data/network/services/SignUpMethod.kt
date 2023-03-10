package com.markusw.app.data.network.services

import com.markusw.app.core.FirebaseClient
import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User
import javax.inject.Inject

abstract class SignUpMethod @Inject constructor(): RegisterService {

    @Inject
    protected lateinit var firebaseClient: FirebaseClient

    abstract override suspend fun registerUser(user: User): AuthenticationResult
}