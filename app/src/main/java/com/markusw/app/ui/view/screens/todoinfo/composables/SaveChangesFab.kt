package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun SaveChangesFab(
    onClick: () -> Unit = { }
) {
    FloatingActionButton(onClick = onClick, shape = CircleShape) {
        Icon(
            imageVector = Icons.Default.Save,
            contentDescription = null
        )
    }
}