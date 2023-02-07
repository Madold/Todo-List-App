package com.markusw.app.domain.model

import com.markusw.app.data.database.entities.UserSettingsEntity

data class UserSettings(
    val id: Int = 1,
    val isDarkModeEnabled: Boolean = false,
    val isDynamicColorsEnabled: Boolean = false,
)

fun UserSettings.toEntity() = UserSettingsEntity(
        id = id,
        isDarkModeEnabled = isDarkModeEnabled,
        isDynamicColorsEnabled = isDynamicColorsEnabled,
    )

fun UserSettingsEntity.toDomainModel() = UserSettings(
        id = id,
        isDarkModeEnabled = isDarkModeEnabled,
        isDynamicColorsEnabled = isDynamicColorsEnabled,
    )

