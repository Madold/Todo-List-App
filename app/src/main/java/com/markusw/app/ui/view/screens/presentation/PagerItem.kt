package com.markusw.app.ui.view.screens.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.markusw.app.R
import com.markusw.app.core.LocalFirebaseClient
import com.markusw.app.domain.AuthenticationEvent
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.presentation.composables.FirstSlide
import com.markusw.app.ui.view.screens.presentation.composables.LoginSlide
import com.markusw.app.ui.view.screens.presentation.composables.SecondSlide
import com.markusw.app.ui.viewmodel.MainViewModel
import com.orhanobut.logger.Logger

sealed class PagerItem(
    val content: @Composable (NavController) -> Unit
) {
    object FirstSlide : PagerItem(
        content = {
            FirstSlide(modifier = Modifier.fillMaxSize())
        }
    )

    object SecondSlide : PagerItem(
        content = {
            SecondSlide(modifier = Modifier.fillMaxSize())
        }
    )

    object LoginSlide : PagerItem(
        content = { navController ->

            val viewModel: MainViewModel = hiltViewModel()
            val googleSignInOptions = rememberGoogleSignInOptions()
            val googleSignInIntent = rememberGoogleSignInIntent(googleSignInOptions)
            val uiState by viewModel.uiState.collectAsState()
            val firebaseClient = LocalFirebaseClient.current
            val launcher = rememberLauncherForActivityResult(contract = StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    firebaseClient.crashlytics.log("Google sign in was successful")
                    GoogleSignIn.getSignedInAccountFromIntent(it.data)
                        .addOnCompleteListener { result ->
                            if (result.isSuccessful) {
                                val credential =
                                    GoogleAuthProvider.getCredential(result.result.idToken, null)
                                Logger.d("Google sign in was successful")
                                viewModel.onGoogleSignInResult(token = credential)
                            }
                        }
                }
            }

            LaunchedEffect(key1 = viewModel) {
                viewModel.authenticationEventChannel.collect { event ->
                    when (event) {
                        is AuthenticationEvent.Failure -> {
                            Logger.d("Authentication failed Error: ${event.reason}")
                        }
                        is AuthenticationEvent.Success -> {
                            Logger.d("Authentication was successful")
                            navController.popBackStack()
                            navController.navigate(Screens.MainScreen.route)
                        }
                    }
                }
            }

            LoginSlide(
                modifier = Modifier.fillMaxSize(),
                emailValue = uiState.email,
                onEmailChange = viewModel::onEmailChanged,
                passwordValue = uiState.password,
                onPasswordChange = viewModel::onPasswordChanged,
                onGoogleButtonClick = {
                    launcher.launch(googleSignInIntent)
                },
                onFaceBookButtonClick = {},
                onRegisterTextClick = {
                    navController.navigate(route = Screens.RegisterScreen.route)
                }
            )
        }
    )
}

@Composable
fun rememberGoogleSignInOptions(): GoogleSignInOptions {
    val context = LocalContext.current

    return remember {
        GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
}

@Composable
fun rememberGoogleSignInIntent(signInOptions: GoogleSignInOptions): Intent {
    val context = LocalContext.current

    return remember {
        GoogleSignIn.getClient(context, signInOptions).signInIntent
    }
}
