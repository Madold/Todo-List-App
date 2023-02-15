@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.todoinfo.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.markusw.app.R

@Composable
fun TodoInfoTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.task_information_text))
        },
        navigationIcon = {
            IconButton(
                onClick = {  navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}