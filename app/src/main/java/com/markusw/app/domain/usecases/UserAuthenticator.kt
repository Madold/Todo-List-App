package com.markusw.app.domain.usecases

import com.markusw.app.data.network.services.AuthService
import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User
import javax.inject.Inject

class UserAuthenticator @Inject constructor() {
    suspend fun authenticate(user: User, method: AuthService): AuthenticationResult {
        return method.authenticate(user)
    }
}