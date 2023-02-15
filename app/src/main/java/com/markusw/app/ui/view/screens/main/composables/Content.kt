package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.markusw.app.ui.viewmodel.MainViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun Content(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val taskList by viewModel.todoList.collectAsState(initial = emptyList())
    val isTaskListEmpty by remember { derivedStateOf { taskList.isEmpty() } }
    val taskCounterText by remember {
        derivedStateOf {
            if (taskList.isNotEmpty()) taskList.size.toString() else ""
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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(taskList) { task ->

                    val deleteAction = SwipeAction(
                        onSwipe = {
                            viewModel.deleteTodo(task)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.inverseOnSurface
                            )
                        },
                        background = MaterialTheme.colorScheme.inverseSurface
                    )

                    SwipeableActionsBox(
                        endActions = listOf(deleteAction),
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                        swipeThreshold = 135.dp
                    ) {
                        TaskItem(
                            task = task,
                            navController = navController
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                AnimatedVisibility(visible = isTaskListEmpty, enter = fadeIn(
                    animationSpec = tween(
                        delayMillis = 300
                    )
                ), exit = fadeOut()) {
                    EmptyListAnimation()
                }
            }
        }
    }
}