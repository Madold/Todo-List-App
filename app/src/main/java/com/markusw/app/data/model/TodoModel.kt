package com.markusw.app.data.model

data class TodoModel(
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val id: Int = 0
)

