package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.markusw.app.R

@Composable
fun TaskDateField(
    value: String,
    placeholder: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    isError: Boolean = false,
    errorMessage: String? = null,
    onClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.remember_task_text)
        )
        DialogField(
            value = value,
            placeholder = placeholder,
            trailingIcon = trailingIcon,
            onClick = onClick,
            isError = isError
        )
        AnimatedVisibility(visible = isError) {
            ErrorText(errorMessage = errorMessage)
        }
    }


}
