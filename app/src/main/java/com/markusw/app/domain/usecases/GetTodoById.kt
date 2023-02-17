package com.markusw.app.domain.usecases

import com.markusw.app.data.Repository
import javax.inject.Inject

class GetTodoById @Inject constructor(
    private val repository: Repository
) {
   suspend operator fun invoke(id: Int) = repository.getTodoById(id)
}