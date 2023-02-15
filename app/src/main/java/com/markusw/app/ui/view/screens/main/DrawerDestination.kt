package com.markusw.app.ui.view.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import com.markusw.app.ui.view.screens.Screens

sealed class DrawerDestination(
    val label: UiText,
    val route: String,
    val icon: ImageVector
) {
    
    object SettingsScreen: DrawerDestination(
        label = UiText.StringResource(id = R.string.settings_text),
        route = Screens.SettingsScreen.route,
        icon = Icons.Default.Settings
    )

}