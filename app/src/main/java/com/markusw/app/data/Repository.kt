package com.markusw.app.data

import com.markusw.app.data.database.dao.SettingsDao
import com.markusw.app.data.database.dao.TodoDao
import com.markusw.app.domain.model.Todo
import com.markusw.app.domain.model.UserSettings
import com.markusw.app.domain.model.toDomainModel
import com.markusw.app.domain.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val todoDao: TodoDao,
    private val settingsDao: SettingsDao
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

    fun getTodoById(id: Int): Flow<Todo> {
        return todoDao.getTodoById(id).map { it.toDomainModel() }
    }

    fun getSettings(): Flow<UserSettings?> {
        return settingsDao.getSettings().map {settings->
            settings.let {
                it?.toDomainModel()
            } ?: UserSettings()
        }
    }

    suspend fun saveSettings(settings: UserSettings) {
        settingsDao.saveSettings(settings.toEntity())
    }

}