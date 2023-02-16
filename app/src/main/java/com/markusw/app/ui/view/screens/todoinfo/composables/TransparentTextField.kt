package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 16.sp,
    onFocusChanged: (Boolean) -> Unit
) {

    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.hasFocus
                    onFocusChanged(isFocused)
                },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = fontSize
            ),
            cursorBrush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.onBackground,
                    MaterialTheme.colorScheme.onBackground
                )
            )
        )
        if (isFocused && value.isBlank() || !isFocused && value.isBlank()) {
            Text(text = placeholder)
        }
    }
}