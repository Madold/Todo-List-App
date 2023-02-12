package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskHourField(
    value: String,
    placeholder: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onClick: () -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "At"
        )
        Spacer(modifier = Modifier.height(8.dp))
        DialogField(
            value = value,
            placeholder = placeholder,
            trailingIcon = trailingIcon,
            onClick = onClick,
            isError = isError,
        )
        if (isError) {
            ErrorText(errorMessage = errorMessage)
        }
    }
}
