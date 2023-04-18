package com.stanroy.todolist.presentation.screen_task_list

import com.stanroy.todolist.domain.model.TodoTask

data class ListScreenState(
    val isLoading: Boolean = false,
    val isReloading: Boolean = false,
    val tasks: List<TodoTask> = emptyList()
)
