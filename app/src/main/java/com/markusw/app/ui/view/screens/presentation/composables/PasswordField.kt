package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    label: @Composable () -> Unit = {},
    onDone: () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
) {
    BaseField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        isError = isError,
        errorMessage = errorMessage,
        label = label,
        onDone = onDone,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        singleLine = singleLine
    )
}