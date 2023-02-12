package com.markusw.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markusw.app.core.Constants.TODOS_TABLE
import com.markusw.app.data.database.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM $TODOS_TABLE")
    fun getTodoList(): Flow<List<TodoEntity>>

    @Query("DELETE FROM $TODOS_TABLE")
    suspend fun deleteAllTodos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(todo: TodoEntity)

    @Query("SELECT * FROM $TODOS_TABLE WHERE id = :id")
    fun getTodoById(id: Int): Flow<TodoEntity>

    @Query("DELETE FROM $TODOS_TABLE WHERE id = :id")
    suspend fun deleteTodoById(id: Int)
}