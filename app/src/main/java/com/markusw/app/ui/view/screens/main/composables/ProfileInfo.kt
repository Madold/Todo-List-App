package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.SubcomposeAsyncImage
import com.markusw.app.core.LocalFirebaseClient
import com.markusw.app.ui.theme.spacing

@Composable
fun ProfileInfo() {

    val currentUser = LocalFirebaseClient.current.currentUser

    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        SubcomposeAsyncImage(
            model = currentUser?.photoUrl,
            contentDescription = null,
            loading = {
                CircularProgressIndicator()
            },
            modifier = Modifier.clip(CircleShape)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(text = "Signed as ${currentUser?.displayName ?: "Unknown"}")
            Text(text = currentUser?.email ?: "example@example.com")
        }
    }
}