package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.runtime.Composable

@Composable
fun TaskDateField(
    value: String,
    placeholder: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onClick: () -> Unit
) {
    DialogField(
        value = value,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        onClick = onClick
    )
}
