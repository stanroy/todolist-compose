package com.stanroy.todolist.data.common

import com.stanroy.todolist.data.db.entities.TaskEntity
import com.stanroy.todolist.domain.model.TodoTask

fun TaskEntity.toTodoTask(): TodoTask = TodoTask(this.title, this.description)

fun TodoTask.toTaskEntity(): TaskEntity =
    TaskEntity(title = this.title, description = this.description)