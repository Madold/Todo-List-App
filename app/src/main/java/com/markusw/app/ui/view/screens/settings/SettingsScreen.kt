@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.markusw.app.ui.view.screens.settings.composables.Content
import com.markusw.app.ui.view.screens.settings.composables.SettingScreenTopBar

@Composable
fun SettingsScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            SettingScreenTopBar(
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
        },
    ) {padding ->
        Content(paddingValues = padding)
    }
}