@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.markusw.app.domain.model.UserSettings
import com.markusw.app.ui.theme.AppTheme
import com.markusw.app.ui.theme.md_theme_light_background
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.main.MainScreen
import com.markusw.app.ui.view.screens.presentation.PresentationScreen
import com.markusw.app.ui.view.screens.register.RegisterScreen
import com.markusw.app.ui.view.screens.settings.SettingsScreen
import com.markusw.app.ui.view.screens.todoinfo.TaskInfoScreen
import com.markusw.app.ui.view.screens.writtetodo.WriteTodoScreen
import com.markusw.app.ui.viewmodel.MainViewModel
import com.orhanobut.logger.Logger

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavHost(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val currentUser = viewModel.firebaseClient.currentUser
    val startDestination =
        if (currentUser != null) Screens.MainScreen.route else "${Screens.PresentationScreen.route}?selectedSlide={selectedSlide}"
    val userSettings by viewModel.settings.collectAsState(initial = UserSettings())
    val systemUiController = rememberSystemUiController()
    var systemBarsColors: Color
    val isDarkModeEnabled by remember {
        derivedStateOf { userSettings?.isDarkModeEnabled ?: false }
    }
    val isDynamicColorEnabled by remember {
        derivedStateOf { userSettings?.isDynamicColorsEnabled ?: false }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
        ) {

            // Register Screen
            composable(route = Screens.RegisterScreen.route) {
                AppTheme(
                    useDarkTheme = false,
                    dynamicColor = false
                ) {
                    RegisterScreen(navController = navController)
                }
            }

            // Presentation Screen
            composable(
                route = "${Screens.PresentationScreen.route}?selectedSlide={selectedSlide}",
                arguments = listOf(
                    navArgument(name = "selectedSlide") {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) {

                val selectedSlide = it.arguments?.getInt("selectedSlide")

                AppTheme(
                    useDarkTheme = false,
                    dynamicColor = false
                ) {
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = md_theme_light_background,
                            darkIcons = true
                        )
                    }

                    PresentationScreen(
                        navController = navController,
                        selectedSlide = selectedSlide ?: 1
                    )
                }
            }


            // Main Screen
            composable(
                route = Screens.MainScreen.route,
            ) {

                AppTheme(
                    useDarkTheme = isDarkModeEnabled,
                    dynamicColor = isDynamicColorEnabled
                ) {

                    systemBarsColors = MaterialTheme.colorScheme.background

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = systemBarsColors,
                            darkIcons = !isDarkModeEnabled
                        )
                    }

                    MainScreen(navController = navController)
                }
            }

            // Write Todo Screen
            composable(
                route = Screens.WriteTodoScreen.route,
                enterTransition = {
                    scaleIn()
                },
                popExitTransition = {
                    scaleOut()
                }
            ) {

                AppTheme(
                    useDarkTheme = isDarkModeEnabled,
                    dynamicColor = isDynamicColorEnabled
                ) {

                    systemBarsColors = MaterialTheme.colorScheme.background

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = systemBarsColors,
                            darkIcons = !isDarkModeEnabled
                        )
                    }

                    WriteTodoScreen(navController = navController)
                }
            }

            // Todo Info Screen
            composable(
                route = "${Screens.TodoInfoScreen.route}/{id}",
                arguments = listOf(
                    navArgument(name = "id") {
                        type = NavType.IntType
                    }
                ),
                enterTransition = {
                    expandHorizontally()
                }, popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -1300 },
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    )
                }
            ) {
                AppTheme(
                    useDarkTheme = isDarkModeEnabled,
                    dynamicColor = isDynamicColorEnabled
                ) {

                    systemBarsColors = MaterialTheme.colorScheme.background

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = systemBarsColors,
                            darkIcons = !isDarkModeEnabled
                        )
                    }

                    TaskInfoScreen(
                        navController = navController,
                    )
                }
            }

            // Settings Screen
            composable(
                route = Screens.SettingsScreen.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { 1300 },
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    )
                },
                popExitTransition = {
                    shrinkHorizontally(
                        animationSpec = tween(
                            durationMillis = 700
                        ),
                        shrinkTowards = Alignment.CenterHorizontally
                    )
                }

            ) {
                AppTheme(
                    useDarkTheme = isDarkModeEnabled,
                    dynamicColor = isDynamicColorEnabled
                ) {

                    systemBarsColors = MaterialTheme.colorScheme.background

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = systemBarsColors,
                            darkIcons = !isDarkModeEnabled
                        )
                    }

                    SettingsScreen(navController = navController)
                }
            }
        }
    }
}