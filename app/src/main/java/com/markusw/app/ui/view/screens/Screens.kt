package com.markusw.app.ui.view.screens

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object TodoInfoScreen : Screens("todo_info_screen")
}