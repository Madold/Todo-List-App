@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    value: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    maxLines: Int = 1,
    trailingIcon: @Composable (() -> Unit) = {},
    readOnly: Boolean = false,
    placeholderText: String = "",
    onValueChange: (String) -> Unit,
    colors: TextFieldColors,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholderText)
        },
        modifier = modifier.fillMaxWidth(),
        colors = colors,
        shape = RoundedCornerShape(8.dp),
        singleLine = singleLine,
        maxLines = maxLines,
        trailingIcon = trailingIcon,
        readOnly = readOnly,
        isError = isError
    )
}