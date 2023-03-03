package com.markusw.app.ui.view.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.markusw.app.R
import com.markusw.app.ui.view.UiText
import com.markusw.app.ui.view.screens.Screens

sealed class DrawerDestination(
    val label: UiText,
    val route: String,
    @DrawableRes val icon: Int
) {
    
    object SettingsScreen: DrawerDestination(
        label = UiText.StringResource(id = R.string.settings_text),
        route = Screens.SettingsScreen.route,
        icon = R.drawable.ic_settings
    )

}