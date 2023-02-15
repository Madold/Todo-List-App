package com.markusw.app.ui.view.screens.settings.composables

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markusw.app.R
import com.markusw.app.domain.model.UserSettings
import com.markusw.app.ui.viewmodel.SettingsViewModel

@Composable
fun Content(
    paddingValues: PaddingValues,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val settings by viewModel.settings.collectAsState(initial = UserSettings())
    val isDarkModeEnabled by remember {
        derivedStateOf {
            settings?.isDarkModeEnabled ?: false
        }
    }
    val isDynamicColorsEnabled by remember {
        derivedStateOf {
            settings?.isDynamicColorsEnabled ?: false
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = stringResource(id = R.string.preferences_text))
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.dark_mode_text))
            Switch(
                checked = isDarkModeEnabled,
                onCheckedChange = {
                    val newSettings = settings?.copy(isDarkModeEnabled = it) ?: UserSettings(isDarkModeEnabled = it)
                    viewModel.saveSettings(newSettings)
                }
            )
        }

        // Dynamic colors are only available on Android 12+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.dynamic_colors_text))
                Switch(
                    checked = isDynamicColorsEnabled,
                    onCheckedChange = {
                        val newSettings = settings?.copy(isDynamicColorsEnabled = it) ?: UserSettings(isDynamicColorsEnabled = it)
                        viewModel.saveSettings(newSettings)
                    }
                )
            }
        }
    }
}