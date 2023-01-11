@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main.composables

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import java.time.LocalTime
import java.util.*

@Composable
fun TopBar() {

    val greetingText by getGreeting()

    TopAppBar(
        title = {
            Text(
                text = greetingText,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
fun getGreeting(): State<String> {
    return produceState(initialValue = "") {
        value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when (LocalTime.now().hour) {
                in (0 until 12) -> {
                    "Buenos días"
                }
                in (12 until 18) -> {
                    "Buenas tardes"
                }
                else -> {
                    "Buenas noches"
                }
            }
        } else {
            "Bienvenido"
        }
    }
}
