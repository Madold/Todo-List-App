package com.markusw.app.data.network.services

import com.markusw.app.domain.AuthenticationResult
import com.markusw.app.domain.model.User
import javax.inject.Inject

class FirebaseSignInMethod @Inject constructor(): SignInMethod() {

    override suspend fun authenticate(user: User): AuthenticationResult {
        return try {
            var result: AuthenticationResult? = null

            firebaseClient.auth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {

                    if (!it.isSuccessful) {
                        result = AuthenticationResult(
                            success = false,
                            errorMessage = it.exception?.message
                        )
                        return@addOnCompleteListener
                    }

                    result = AuthenticationResult(success = true)
                }
            result!!
        } catch (e: Exception) {
            AuthenticationResult(
                success = false,
                errorMessage = e.message
            )
        }
    }
}