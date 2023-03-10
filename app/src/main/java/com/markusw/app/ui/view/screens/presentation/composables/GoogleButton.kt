package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.markusw.app.R

@Composable
fun GoogleButton(
        onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick,
            shape = CircleShape,
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            modifier = Modifier.scale(0.84f)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = null)
    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton {

    }
}