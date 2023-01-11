package com.markusw.app.domain.model

import com.markusw.app.data.database.entities.TodoEntity

data class Todo(
    val id: Int = 0,
    val title: String,
    val description: String,
    var isDone: Boolean = false
)

fun TodoEntity.toDomainModel() = Todo(
    title = title,
    description = description,
    isDone = isDone,
    id = id
)

fun Todo.toEntity() = TodoEntity(
    title = title,
    description = description,
    isDone = isDone,
    id = id
)
