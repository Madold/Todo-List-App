@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package com.markusw.app.ui.view.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.markusw.app.ui.view.screens.main.composables.BottomBar
import com.markusw.app.ui.view.screens.main.composables.BottomNavigationHost
import com.markusw.app.ui.view.screens.main.composables.TopBar

@Composable
fun MainScreen() {
    val bottomNavController = rememberAnimatedNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(bottomNavController)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        BottomNavigationHost(
            navHostController = bottomNavController,
            paddingValues = padding,
            snackbarHostState = snackbarHostState
        )
    }
}