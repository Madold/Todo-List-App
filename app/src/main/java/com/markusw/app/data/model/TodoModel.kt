package com.markusw.app.data.model

import java.time.LocalDate

data class TodoModel(
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val id: Int = 0,
)
