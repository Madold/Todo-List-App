package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.markusw.app.ui.view.screens.writtetodo.composables.ErrorText

@Composable
fun EditDescriptionField(
    fontSize: TextUnit = 16.sp,
    value: String = "",
    placeholder: String = "",
    singleLine: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit = {}
) {
    TransparentTextField(
        value = value,
        placeholder = placeholder,
        singleLine = singleLine,
        onValueChange = onValueChange,
        onFocusChanged = onFocusChanged,
        fontSize = fontSize
    )
    if (isError) {
        ErrorText(errorMessage)
    }
}