@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markusw.app.ui.view.screens.main.DrawerDestination
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit
) {


    val coroutineScope = rememberCoroutineScope()
    val drawerDestinations = listOf(
        DrawerDestination.SettingsScreen
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
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
                                text = destination.label.asString(),
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = false,
                        onClick = {
                            navController.navigate(destination.route)
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
        content = content
    )
}