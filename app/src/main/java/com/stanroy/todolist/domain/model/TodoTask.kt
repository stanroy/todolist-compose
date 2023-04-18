package com.stanroy.todolist.domain.model

data class TodoTask(
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val isFinished: Boolean = false
)
