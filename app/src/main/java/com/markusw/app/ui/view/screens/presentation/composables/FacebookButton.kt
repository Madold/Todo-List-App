package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.markusw.app.R
import com.markusw.app.ui.theme.OceanBlue

@Composable
fun FacebookButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
            shape = CircleShape,
            containerColor = OceanBlue,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            modifier = Modifier.scale(0.84f)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_facebook),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(26.dp)
        )
    }
}