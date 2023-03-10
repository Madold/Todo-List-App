package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RegisterText(
        onClick: () -> Unit
) {
    Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Don't have an account?")
        Text(
                text = "Register here",
                modifier = Modifier.clickable(onClick = onClick),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
        )
    }
}