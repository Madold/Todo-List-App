
package com.markusw.app.ui.view.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.markusw.app.ui.theme.AppTheme
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.main.MainScreen
import com.markusw.app.ui.view.screens.todoinfo.TodoInfoScreen
import com.markusw.app.ui.view.screens.writtetodo.WriteTodoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                val systemUiController = rememberSystemUiController()
                val isSystemInDarkTheme = isSystemInDarkTheme()
                val systemBarsColors = MaterialTheme.colorScheme.background
                val navController = rememberNavController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemBarsColors,
                        darkIcons = !isSystemInDarkTheme
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.MainScreen.route
                    ) {
                        composable(
                            route = Screens.MainScreen.route,
                        ) {
                            MainScreen(navController)
                        }
                        composable(
                            route = Screens.WriteTodoScreen.route,
                        ) {
                            WriteTodoScreen(navController)
                        }
                        composable(
                            route = "${Screens.TodoInfoScreen.route}/{id}",
                            arguments = listOf(
                                navArgument(name = "id") {
                                    type = NavType.IntType
                                }
                            )
                        ) { navBackStackEntry ->
                            val todoId = navBackStackEntry.arguments?.getInt("id")
                            TodoInfoScreen(
                                navController = navController,
                                todoId = todoId!!
                            )
                        }
                    }
                }
            }
        }
    }
}

