@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.markusw.app.ui.view.screens.writtetodo.composables.ErrorText

@Composable
fun BaseField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    label: @Composable () -> Unit = {},
    onDone: () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            isError = isError,
            label = label,
            keyboardActions = KeyboardActions(onDone = { onDone() }),
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation
        )
        AnimatedVisibility(visible = isError) {
            ErrorText(errorMessage = errorMessage)
        }
    }
}