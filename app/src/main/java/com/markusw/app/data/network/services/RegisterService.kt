package com.markusw.app.data.network.services

import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User

interface RegisterService {
    suspend fun registerUser(user: User): AuthenticationResult
}