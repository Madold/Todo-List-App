package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markusw.app.R
import com.markusw.app.ui.theme.AppTheme

@Composable
fun LoginSlide(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_ilustration),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Text(
            text = "Get started now",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        EmailField(
            value = "someone@gmail.com",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            errorMessage = "Please enter a valid email address",
            label = {
                Text(text = "Email")
            }
        )
        PasswordField(
            value = "*********",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            errorMessage = "Please enter a valid password",
            label = {
                Text(text = "Password")
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Login")
        }
        RegisterText(
            onClick = {

            }
        )
        OneTapSignInOptions(
            onGoogleButtonClick = { },
            onFacebookButtonClick = { }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginSlidePreview() {
    AppTheme(
        dynamicColor = false
    ) {
        LoginSlide()
    }
}