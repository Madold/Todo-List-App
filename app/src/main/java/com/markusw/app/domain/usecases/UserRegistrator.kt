package com.markusw.app.domain.usecases

import com.markusw.app.data.network.services.RegisterService
import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User
import javax.inject.Inject

class UserRegistrator @Inject constructor() {

    suspend fun register(user: User, method: RegisterService): AuthenticationResult {
        return method.registerUser(user)
    }

}