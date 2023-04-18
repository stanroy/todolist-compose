package com.stanroy.todolist.data.common

import com.stanroy.todolist.data.db.entities.TaskEntity
import com.stanroy.todolist.domain.model.TodoTask

fun TaskEntity.toTodoTask(): TodoTask =
    TodoTask(this.uid, this.title, this.description, this.isFinished)

fun TodoTask.toTaskEntity(): TaskEntity =
    TaskEntity(this.id, this.title, this.description, this.isFinished)