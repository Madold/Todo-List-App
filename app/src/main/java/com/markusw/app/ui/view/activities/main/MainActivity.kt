@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package com.markusw.app.ui.view.activities.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.markusw.app.domain.model.UserSettings
import com.markusw.app.ui.theme.AppTheme
import com.markusw.app.ui.view.NavHost
import com.markusw.app.ui.view.screens.main.composables.NavigationDrawer
import com.markusw.app.ui.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val userSettings by settingsViewModel.settings.collectAsState(initial = UserSettings())
            val isDarkModeEnabled by remember {
                derivedStateOf {
                    userSettings?.isDarkModeEnabled ?: false
                }
            }
            val isDynamicColorsEnabled by remember {
                derivedStateOf {
                    userSettings?.isDynamicColorsEnabled ?: false
                }
            }

            AppTheme(
                useDarkTheme = isDarkModeEnabled,
                dynamicColor = isDynamicColorsEnabled
            ) {

                val systemUiController = rememberSystemUiController()
                val systemBarsColors = MaterialTheme.colorScheme.background
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberAnimatedNavController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemBarsColors,
                        darkIcons = !isDarkModeEnabled
                    )
                }

                NavigationDrawer(
                    drawerState = drawerState,
                    content = {
                        NavHost(
                            drawerState = drawerState,
                            navController = navController
                        )
                    },
                    navController = navController
                )
            }
        }
    }
}

