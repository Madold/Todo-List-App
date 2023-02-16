@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.markusw.app.R

@Composable
fun TaskTitleField(
    value: String,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
    containerColor = MaterialTheme.colorScheme.surfaceVariant,
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
),
    errorMessage: String?,
    onFocusChange: (FocusState) -> Unit = { }
) {
    Column {
        Text(text = stringResource(id = R.string.task_title_text))
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            value = value,
            onValueChange = onValueChange,
            placeholderText = placeholderText,
            isError = isError,
            colors = colors,
            modifier = modifier.onFocusChanged { onFocusChange(it) },
        )
        AnimatedVisibility(visible = isError) {
            Spacer(modifier = Modifier.height(8.dp))
            ErrorText(errorMessage = errorMessage)
        }
    }
}
