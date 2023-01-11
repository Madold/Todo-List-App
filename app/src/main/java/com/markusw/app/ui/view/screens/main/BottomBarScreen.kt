package com.markusw.app.ui.view.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val screenName: String,
    val icon: ImageVector
) {

    object HomeScreen: BottomBarScreen(
        route = "home_screen",
        screenName = "Home",
        icon = Icons.Default.Home
    )

    object WriteTodoScreen: BottomBarScreen(
        route = "writeTodo_screen",
        screenName = "Write Todo",
        icon = Icons.Default.Edit
    )

}
