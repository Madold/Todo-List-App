@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.markusw.app.R
import java.time.LocalTime
import java.util.*

@Composable
fun TopBar(
    onNavigationIconClick: () -> Unit
) {

    val greetingText by getGreeting()

    MediumTopAppBar(
        title = {
            Text(
                text = greetingText,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}

@Composable
fun getGreeting(): State<String> {

    val context = LocalContext.current

    return produceState(initialValue = "") {
        value = when (LocalTime.now().hour) {
            in (0 until 12) -> {
                context.getString(R.string.morning_greeting)
            }
            in (12 until 18) -> {
                context.getString(R.string.afternoon_greeting)
            }
            else -> {
                context.getString(R.string.evening_greeting)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(onNavigationIconClick = {})
}