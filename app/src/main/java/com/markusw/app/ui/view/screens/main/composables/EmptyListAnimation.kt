package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.markusw.app.R

@Composable
fun EmptyListAnimation() {
    val compositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.not_found_anim)
    )
    val progressAnimation by animateLottieCompositionAsState(
        composition = compositionResult.value,
        speed = 1f,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = compositionResult.value,
            progress = { progressAnimation },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Nothing here, you're free :)",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.55f),
                fontSize = 20.sp
            )
        )
    }

}