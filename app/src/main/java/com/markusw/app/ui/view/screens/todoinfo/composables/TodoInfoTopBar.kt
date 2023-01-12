@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TodoInfoTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(text = "Informacion de la tarea")
        },
        navigationIcon = {
            IconButton(
                onClick = {  navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
    )
}