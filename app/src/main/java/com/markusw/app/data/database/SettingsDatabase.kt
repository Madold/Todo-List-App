package com.markusw.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markusw.app.data.database.dao.SettingsDao
import com.markusw.app.data.database.entities.UserSettingsEntity

@Database(entities = [UserSettingsEntity::class], version = 1)
abstract class SettingsDatabase: RoomDatabase() {
    abstract fun getSettingsDao(): SettingsDao
}