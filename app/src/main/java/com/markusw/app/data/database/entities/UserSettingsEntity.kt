package com.markusw.app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.markusw.app.core.Constants.USER_SETTINGS_TABLE

@Entity(tableName = USER_SETTINGS_TABLE)
data class UserSettingsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int = 1,
    @ColumnInfo(name = "isDarkModeEnabled") val isDarkModeEnabled: Boolean = false,
    @ColumnInfo(name = "isDynamicColorsEnabled") val isDynamicColorsEnabled: Boolean = false,
)
