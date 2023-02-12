package com.markusw.app.domain.usecases

import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import javax.inject.Inject

class PerformOnTodoDone @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(todo: Todo) {
        todo.isDone = !todo.isDone
        repository.saveTodo(todo)
    }
}