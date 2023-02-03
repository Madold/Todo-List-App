package com.markusw.app.data

import com.markusw.app.data.database.dao.TodoDao
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.model.toDomainModel
import com.markusw.app.domain.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val todoDao: TodoDao
) {
    companion object {
        private const val TAG = "repository"
    }

    fun getTodoList(): Flow<List<Todo>> {
        val response = todoDao.getTodoList()
        return response.map { list ->
            list.map { it.toDomainModel() }
        }
    }

    suspend fun saveTodo(todo: Todo) {
        todoDao.saveTodo(todo.toEntity())
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodoById(todo.id)
    }

    suspend fun deleteAllTodo() {
        todoDao.deleteAllTodos()
    }

}