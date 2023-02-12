package com.markusw.app.di

import android.content.Context
import androidx.room.Room
import com.markusw.app.core.Constants.TODOS_TABLE
import com.markusw.app.core.Constants.USER_SETTINGS_TABLE
import com.markusw.app.data.database.SettingsDatabase
import com.markusw.app.data.database.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideTodosDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, TodosDatabase::class.java, TODOS_TABLE)
        .build()

    @Provides
    @Singleton
    fun provideTodoDao(db: TodosDatabase) = db.getTodoDao()

    @Provides
    @Singleton
    fun provideSettingsDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, SettingsDatabase::class.java, USER_SETTINGS_TABLE)
        .build()

    @Provides
    @Singleton
    fun provideSettingsDao(db: SettingsDatabase) = db.getSettingsDao()

}