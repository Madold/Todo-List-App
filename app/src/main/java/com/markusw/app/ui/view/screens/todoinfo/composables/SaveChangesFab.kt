package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.markusw.app.R

@Composable
fun SaveChangesFab(
    onClick: () -> Unit = { }
) {
    FloatingActionButton(onClick = onClick, shape = CircleShape) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
    }
}