package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markusw.app.domain.model.Todo
import com.markusw.app.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun TodoItem(
    todo: Todo,
    viewModel: MainViewModel,
    navController: NavController
) {

    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .clickable {

            }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = todo.title,
            style = MaterialTheme.typography.bodyLarge
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            var isDone by remember { mutableStateOf(todo.isDone) }
            val defaultTintColor = LocalContentColor.current

            TodoIcon(
                icon = Icons.Default.Check,
                contentDescription = null,
                backgroundColor = if (isDone) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                tintColor = if (isDone) MaterialTheme.colorScheme.background else defaultTintColor,
            ) {
                viewModel.onTodoDone(todo)
                isDone = !isDone
            }
            TodoIcon(
                icon = Icons.Default.Delete,
                contentDescription = null,
                backgroundColor = MaterialTheme.colorScheme.errorContainer,
                tintColor = defaultTintColor
            ) {
                coroutineScope.launch {
                    viewModel.onTodoDeleted(todo)
                }
            }
        }
    }
}

@Composable
fun TodoIcon(
    icon: ImageVector,
    contentDescription: String?,
    cornerRadius: Dp = 8.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    tintColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = backgroundColor)
            .clickable { onClick() }
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tintColor
        )
    }
}
