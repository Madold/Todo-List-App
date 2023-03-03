package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.markusw.app.R

@Composable
fun ScheduleTaskSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.remember_task_text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            thumbContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        )
    }
}