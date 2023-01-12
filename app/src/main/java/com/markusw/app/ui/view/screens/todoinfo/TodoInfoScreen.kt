@file:OptIn(ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.todoinfo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.ui.view.screens.main.composables.TopBar
import com.markusw.app.ui.view.screens.todoinfo.composables.TodoInfoTopBar
import com.markusw.app.ui.viewmodel.TodoInfoViewModel

@Composable
fun TodoInfoScreen(
    navController: NavController,
    todoId: Int,
    viewModel: TodoInfoViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Content(navController = navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Content(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TodoInfoTopBar(navController)
        },
    ) {

    }
}