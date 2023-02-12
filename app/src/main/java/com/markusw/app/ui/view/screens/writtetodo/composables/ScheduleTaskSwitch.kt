package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScheduleTaskSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column {
        Text(
            text = "Remember"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}