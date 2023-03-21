@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.markusw.app.ui.theme.spacing
import com.markusw.app.ui.view.screens.main.DrawerDestination
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerDestinations(
    destinations: List<DrawerDestination>,
    navController: NavController,
    drawerState: DrawerState
) {

    val coroutineScope = rememberCoroutineScope()

    Column {
        destinations.forEach { destination ->
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
                        painter = painterResource(id = destination.icon),
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
                shape = RoundedCornerShape(MaterialTheme.spacing.small)
            )
        }
    }
}