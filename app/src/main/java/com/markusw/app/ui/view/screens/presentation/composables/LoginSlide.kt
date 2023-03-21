package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markusw.app.R
import com.markusw.app.ui.theme.AppTheme
import com.markusw.app.ui.theme.spacing

@Composable
fun LoginSlide(
    emailValue: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    emailErrorMessage: String? = null,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    passwordErrorMessage: String? = null,
    onGoogleButtonClick: () -> Unit,
    onFaceBookButtonClick: () -> Unit,
    onRegisterTextClick: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_ilustration),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(0.32f)
            )
            Text(
                text = "Get started now",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            EmailField(
                value = emailValue,
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                errorMessage = emailErrorMessage,
                label = {
                    Text(text = "Email")
                },
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
            )
            PasswordField(
                value = passwordValue,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                errorMessage = passwordErrorMessage,
                label = {
                    Text(text = "Password")
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Login")
            }
            RegisterText(onClick = onRegisterTextClick)
            OneTapSignInOptions(
                onGoogleButtonClick = onGoogleButtonClick,
                onFacebookButtonClick = onFaceBookButtonClick
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginSlidePreview() {
    AppTheme(dynamicColor = false) {
        LoginSlide(
            emailValue = "Someone@gmail.com",
            onEmailChange = {},
            passwordValue = "**********",
            onPasswordChange = {},
            onFaceBookButtonClick = {},
            onGoogleButtonClick = {},
            onRegisterTextClick = {}
        )
    }
}