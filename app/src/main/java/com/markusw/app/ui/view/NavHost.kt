@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.main.MainScreen
import com.markusw.app.ui.view.screens.settings.SettingsScreen
import com.markusw.app.ui.view.screens.todoinfo.TaskInfoScreen
import com.markusw.app.ui.view.screens.writtetodo.WriteTodoScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavHost(
    drawerState: DrawerState,
    navController: NavHostController
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = Screens.MainScreen.route
        ) {
            composable(
                route = Screens.MainScreen.route,
            ) {
                MainScreen(
                    navController = navController,
                    drawerState = drawerState
                )
            }
            composable(
                route = Screens.WriteTodoScreen.route,
                enterTransition = {
                    scaleIn()
                },
                popExitTransition = {
                    scaleOut()
                }
            ) {
                WriteTodoScreen(navController)
            }
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
                TaskInfoScreen(
                    navController = navController,
                )
            }
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
                SettingsScreen(navController = navController)
            }
        }
    }
}