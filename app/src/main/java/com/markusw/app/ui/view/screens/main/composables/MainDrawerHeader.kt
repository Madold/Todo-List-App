@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.markusw.app.ui.theme.spacing
import com.markusw.app.ui.view.screens.main.DrawerDestination

@Composable
fun MainDrawerHeader(
    drawerDestinations: List<DrawerDestination>,
    navController: NavController,
    drawerState: DrawerState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        ProfileInfo()
        Divider()
        NavigationDrawerDestinations(
            destinations = drawerDestinations,
            navController = navController,
            drawerState = drawerState
        )
    }
}