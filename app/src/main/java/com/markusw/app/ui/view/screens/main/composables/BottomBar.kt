package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.markusw.app.ui.view.screens.main.BottomBarScreen
import com.markusw.app.ui.viewmodel.MainViewModel

@Composable
fun BottomBar(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        var selectedScreen by remember { mutableStateOf(BottomBarScreen.HomeScreen.route) }

        viewModel.bottomBarScreens.forEach { screen ->
            BottomBarItem(
                screen = screen,
                selected = screen.route == selectedScreen,
            ) {
                selectedScreen = screen.route
                navController.navigate(screen.route)
            }
        }
    }
}

@Composable
fun BottomBarItem(
    screen: BottomBarScreen,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.surfaceVariant
            )
            .clickable { onClick(screen.route) }
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = screen.icon,
            contentDescription = screen.route,
            tint = if (selected) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.scale(1.01f)
        )
        AnimatedVisibility(visible = selected) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = screen.screenName,
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}