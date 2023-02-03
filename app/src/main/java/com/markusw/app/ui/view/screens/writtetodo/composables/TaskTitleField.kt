package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.runtime.Composable

@Composable
fun TaskTitleField(
    value: String,
    placeholderText: String = "",
    onValueChange: (String) -> Unit
) {
    InputField(
        value = value,
        onValueChange = onValueChange,
        placeholderText = placeholderText
    )
}
