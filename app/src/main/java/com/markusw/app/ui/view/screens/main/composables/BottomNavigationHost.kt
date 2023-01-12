@file:OptIn(ExperimentalAnimationApi::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.*
import com.markusw.app.ui.view.screens.main.BottomBarScreen

@Composable
fun BottomNavigationHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.HomeScreen.route
    ) {
        composable(route = BottomBarScreen.HomeScreen.route, enterTransition = {
            slideIntoContainer(
                animationSpec = tween(durationMillis = 300),
                initialOffset = { 1000 },
                towards = AnimatedContentScope.SlideDirection.Left
            )
        }, exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(durationMillis = 300),
                towards = AnimatedContentScope.SlideDirection.Right
            )
        }) {
            HomeScreen(
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
                navController = navHostController
            )
        }
        composable(route = BottomBarScreen.WriteTodoScreen.route) {
            WriteTodoScreen(paddingValues, snackbarHostState)
        }
    }
}