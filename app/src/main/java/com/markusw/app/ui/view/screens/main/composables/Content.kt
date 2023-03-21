package com.markusw.app.ui.view.screens.main.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.R
import com.markusw.app.domain.SignOutEvent
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun MainScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val taskList by viewModel.todoList.collectAsState(initial = emptyList())
    val taskCounterText by remember {
        derivedStateOf {
            if (taskList.isNotEmpty()) taskList.size.toString() else ""
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(key1 = viewModel) {
        viewModel.logOutEventChannel.collect { signOutEvent ->
            val lastSlideIndex = 2

            when (signOutEvent) {
                is SignOutEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Successfully signed out",
                        Toast.LENGTH_SHORT
                    ).show()
                    delay(500)
                    navController.popBackStack()
                    navController.navigate(
                        route = "${Screens.PresentationScreen.route}?selectedSlide=${lastSlideIndex}"
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("${stringResource(id = R.string.tasks_text)} ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(taskCounterText)
                }
            },
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            if (uiState.isLoading) {
                ShimmerLoading(
                    itemsCount = 8
                )
            } else {
                TaskList(
                    list = taskList,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}
