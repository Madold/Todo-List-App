package com.markusw.app.ui.view.screens

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object TodoInfoScreen : Screens("todo_info_screen")
    object WriteTodoScreen : Screens("write_todo_screen")
    object SettingsScreen : Screens("settings_screen")
}