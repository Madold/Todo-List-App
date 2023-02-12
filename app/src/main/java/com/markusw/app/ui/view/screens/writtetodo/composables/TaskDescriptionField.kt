package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskDescriptionField(
    value: String,
    placeholderText: String = "",
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String?
) {
    Column {
        Text(
            text = "Description"
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            value = value,
            singleLine = false,
            maxLines = 4,
            onValueChange = onValueChange,
            placeholderText = placeholderText,
            isError = isError
        )
        AnimatedVisibility(visible = isError) {
            Spacer(modifier = Modifier.height(8.dp))
            ErrorText(errorMessage = errorMessage)
        }
    }
}