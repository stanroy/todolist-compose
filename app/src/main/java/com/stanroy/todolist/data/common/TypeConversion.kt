package com.stanroy.todolist.data.common

import com.stanroy.todolist.data.db.entities.Task
import com.stanroy.todolist.domain.model.TodoTask

fun Task.toTodoTask(): TodoTask = TodoTask(this.title, this.description)