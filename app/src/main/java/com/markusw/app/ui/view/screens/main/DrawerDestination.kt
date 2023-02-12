package com.markusw.app.ui.view.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.markusw.app.ui.view.screens.Screens

sealed class DrawerDestination(
    val label: String,
    val route: String,
    val icon: ImageVector
) {
    object MainScreen: DrawerDestination(
        label = "My tasks",
        route = Screens.MainScreen.route,
        icon = Icons.Default.Check
    )

    object SettingsScreen: DrawerDestination(
        label = "Settings",
        route = Screens.SettingsScreen.route,
        icon = Icons.Default.Settings
    )

}