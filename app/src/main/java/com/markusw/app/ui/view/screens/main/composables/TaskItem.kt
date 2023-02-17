package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markusw.app.domain.model.Todo
import com.markusw.app.ui.view.screens.Screens
import com.markusw.app.ui.viewmodel.MainViewModel

@Composable
fun TaskItem(
    task: Todo,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    var isChecked by remember { mutableStateOf(task.isDone) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("${Screens.TodoInfoScreen.route}/${task.id}")
            }
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TaskCheck(isChecked = isChecked) {
            isChecked = !isChecked
            viewModel.onTodoDone(task)
        }
        Text(
            text = task.title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp,
                textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
            )
        )
    }
}
