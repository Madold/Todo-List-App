@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.ui.theme.spacing
import com.markusw.app.ui.view.screens.main.DrawerDestination
import com.markusw.app.ui.viewmodel.MainViewModel

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val drawerDestinations = listOf(
        DrawerDestination.SettingsScreen
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(
                    topEnd = MaterialTheme.spacing.small,
                    bottomEnd = MaterialTheme.spacing.small
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = MaterialTheme.spacing.medium),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    MainDrawerHeader(
                        drawerDestinations = drawerDestinations,
                        navController = navController,
                        drawerState = drawerState
                    )
                    MainDrawerFooter(onSignOutBtnClick = viewModel::onSignOut)
                }
            }
        },
        content = content
    )
}