package com.markusw.app.data

import android.util.Log
import com.markusw.app.data.database.dao.TodoDao
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.model.toDomainModel
import com.markusw.app.domain.model.toEntity
import javax.inject.Inject


class Repository @Inject constructor(
    private val todoDao: TodoDao
) {

    private val TAG = "repository"

    suspend fun getTodoList(): List<Todo> {
        val response = todoDao.getTodoList()
        return response.map { it.toDomainModel() }
    }

    suspend fun saveTodo(todo: Todo) {
        Log.d(TAG, "saveTodo: id = ${todo.id}")
        todoDao.saveTodo(todo.toEntity())
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodoById(todo.id)
    }

    suspend fun deleteAllTodo() {
        todoDao.deleteAllTodos()
    }

}