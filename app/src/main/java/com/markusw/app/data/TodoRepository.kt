package com.markusw.app.data

import com.markusw.app.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodoList(): Flow<List<Todo>>
    suspend fun saveTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun deleteAllTodo()
    fun getTodoById(id: Int): Flow<Todo>
}