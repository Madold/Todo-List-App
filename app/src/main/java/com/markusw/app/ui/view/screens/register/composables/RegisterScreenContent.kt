package com.markusw.app.ui.view.screens.register.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.markusw.app.ui.view.screens.presentation.composables.EmailField
import com.markusw.app.ui.view.screens.presentation.composables.PasswordField

@Composable
fun RegisterScreenContent(
    paddingValues: PaddingValues,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    repeatedPasswordValue: String,
    onRepeatedPasswordChange: (String) -> Unit,
    emailError: String? = null,
    passwordError: String? = null,
    repeatedPasswordError: String? = null,
) {

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.register_ilustration),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.68f)
            )
            Text(
                text = "Register",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            EmailField(
                modifier = Modifier.fillMaxWidth(),
                value = emailValue,
                onValueChange = onEmailChange,
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                errorMessage = emailError,
                label = {
                    Text(text = "Email")
                }
            )
            PasswordField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordValue,
                onValueChange = onPasswordChange,
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                errorMessage = passwordError,
                label = {
                    Text(text = "Password")
                }
            )
            PasswordField(
                modifier = Modifier.fillMaxWidth(),
                value = repeatedPasswordValue,
                onValueChange = onRepeatedPasswordChange,
                onDone = {
                    focusManager.clearFocus()
                },
                errorMessage = repeatedPasswordError,
                label = {
                    Text(text = "Repeat password")
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(
                    vertical = MaterialTheme.spacing.small,
                )
            ) {
                Text(text = "Register", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegisterScreenContentPreview() {
    AppTheme(
        dynamicColor = false
    ) {
        RegisterScreenContent(
            paddingValues = PaddingValues(0.dp),
            emailValue = "someone@gmail.com",
            onEmailChange = {},
            passwordValue = "*******",
            onPasswordChange = {},
            repeatedPasswordValue = "*******",
            onRepeatedPasswordChange = {}
        )
    }
}