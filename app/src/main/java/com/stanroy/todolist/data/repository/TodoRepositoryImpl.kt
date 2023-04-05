package com.stanroy.todolist.data.repository

import com.stanroy.todolist.data.common.toTodoTask
import com.stanroy.todolist.data.db.dao.TasksDao
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl(val tasksDao: TasksDao) : TodoRepository {
    override suspend fun getAllTodoTasks(): List<TodoTask> = tasksDao.getAll().map { it.toTodoTask() }
}