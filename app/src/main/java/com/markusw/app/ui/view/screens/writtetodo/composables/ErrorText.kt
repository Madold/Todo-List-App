package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorText(
    errorMessage: String?
) {
    Text(
        text = errorMessage ?: "",
        color = MaterialTheme.colorScheme.error
    )
}