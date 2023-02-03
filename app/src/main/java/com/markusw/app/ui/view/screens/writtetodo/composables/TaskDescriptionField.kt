package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.runtime.Composable

@Composable
fun TaskDescriptionField(
    value: String,
    placeholderText: String = "",
    onValueChange: (String) -> Unit
) {
    InputField(
        value = value,
        singleLine = false,
        maxLines = 4,
        onValueChange = onValueChange,
        placeholderText = placeholderText
    )
}