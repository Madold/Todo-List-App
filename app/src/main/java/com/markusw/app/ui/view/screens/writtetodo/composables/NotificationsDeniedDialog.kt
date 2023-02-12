package com.markusw.app.ui.view.screens.writtetodo.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.markusw.app.R

@Composable
fun NotificationDeniedDialog(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.height(200.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CardContent(
                            onConfirm = onConfirm
                        )
                    }
                }
            }
            HeaderAnimation(
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.TopCenter)
            )
        }

    }
}

@Composable
fun HeaderAnimation(
    modifier: Modifier = Modifier
) {
    val compositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.warning_animation))
    val progress by animateLottieCompositionAsState(
        composition = compositionResult.value,
        speed = 1f,
        iterations = 3,
        isPlaying = true
    )

    LottieAnimation(
        composition = compositionResult.value,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun CardContent(onConfirm: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Warning!", style = TextStyle(
                fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Notification permission is needed to remember you your tasks!",
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "If you deny again the permission then you have to enable it manually in settings.",
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onConfirm,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ok")
        }
    }
}