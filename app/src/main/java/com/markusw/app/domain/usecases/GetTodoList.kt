package com.markusw.app.domain.usecases

import com.markusw.app.data.Repository
import javax.inject.Inject

class GetTodoList @Inject constructor(
    private val repository: Repository
) {
   operator fun invoke() = repository.getTodoList()
}