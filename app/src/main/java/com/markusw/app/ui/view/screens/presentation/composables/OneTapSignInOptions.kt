package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OneTapSignInOptions(
    onGoogleButtonClick: () -> Unit,
    onFacebookButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Or login with")
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GoogleButton(onClick = onGoogleButtonClick)
            FacebookButton(onClick = onFacebookButtonClick)
        }
    }
}