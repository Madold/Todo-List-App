package com.markusw.app.domain.usecases

import com.markusw.app.data.Repository
import com.markusw.app.domain.model.Todo
import javax.inject.Inject

class SaveTask @Inject constructor(
    private val repository: Repository
) {
   suspend operator fun invoke(task: Todo) {
       repository.saveTodo(task)
   }
}