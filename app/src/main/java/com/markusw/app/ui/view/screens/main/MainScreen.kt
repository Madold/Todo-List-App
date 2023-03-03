@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.main.composables.Content
import com.markusw.app.ui.view.screens.main.composables.TopBar
import kotlinx.coroutines.launch
import com.markusw.app.R

@Composable
fun MainScreen(
    navController: NavController,
    drawerState: DrawerState,
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val animatedBlur by animateDpAsState(
        targetValue = if (drawerState.isOpen) 10.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 200
        )
    )
    Scaffold(
        topBar = {
            TopBar(
                onNavigationIconClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screens.WriteTodoScreen.route)
                },
                modifier = Modifier.clip(CircleShape),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 16.dp,
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        modifier = Modifier.blur(animatedBlur)
    ) { padding ->
        Content(
            paddingValues = padding,
            navController = navController
        )
    }

}

