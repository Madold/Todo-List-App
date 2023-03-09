package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.markusw.app.R

@Composable
fun GoogleButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = null
        )
    }
}