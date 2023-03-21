package com.markusw.app.data.network.services

import com.google.firebase.auth.AuthCredential
import com.markusw.app.core.FirebaseClient
import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleSignInMethod @Inject constructor(
    private val firebaseClient: FirebaseClient
): AuthService {

    override suspend fun authenticate(user: User): AuthenticationResult {
        return try {
            var result: AuthenticationResult? = null
            firebaseClient.auth.signInWithCredential(user.token as AuthCredential)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        result = AuthenticationResult(
                            success = false,
                            errorMessage = it.exception?.message
                        )
                        return@addOnCompleteListener
                    }
                    result = AuthenticationResult(
                        success = true,
                    )
                }.await()
            result!!
        } catch (e: Exception) {
            AuthenticationResult(
                success = false,
                errorMessage = e.message
            )
        }
    }
}