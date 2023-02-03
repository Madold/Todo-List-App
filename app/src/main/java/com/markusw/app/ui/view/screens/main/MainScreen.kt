@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.view.screens.main.composables.Content
import com.markusw.app.ui.view.screens.main.composables.TopBar
import com.markusw.app.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val drawerDestinations = viewModel.drawerDestinations
    var selectedDestination by remember { mutableStateOf(drawerDestinations.first()) }
    val animatedBlur by animateDpAsState(
        targetValue = if (drawerState.isOpen) 10.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 200
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(
                    topEnd = 8.dp,
                    bottomEnd = 8.dp
                )
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                drawerDestinations.forEach { destination ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = destination.label,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null,
                            )
                        },
                        selected = selectedDestination == destination,
                        onClick = {
                            selectedDestination = destination
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        },
        content = {
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
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
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
    )
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController()
    )
}