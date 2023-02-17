package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markusw.app.domain.model.Todo
import com.markusw.app.ui.viewmodel.MainViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun TaskList(
    list: List<Todo>,
    viewModel: MainViewModel,
    navController: NavController
) {


    Box {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(list) { task ->

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
            AnimatedVisibility(
                visible = list.isEmpty(), enter = fadeIn(
                    animationSpec = tween(
                        delayMillis = 300
                    )
                ), exit = fadeOut()
            ) {
                EmptyListAnimation()
            }
        }
    }
}