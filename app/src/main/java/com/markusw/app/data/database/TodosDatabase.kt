package com.markusw.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markusw.app.data.database.dao.TodoDao
import com.markusw.app.data.database.entities.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodosDatabase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}
