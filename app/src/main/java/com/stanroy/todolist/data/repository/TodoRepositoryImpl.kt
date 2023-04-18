package com.stanroy.todolist.data.repository

import com.stanroy.todolist.data.common.toTaskEntity
import com.stanroy.todolist.data.common.toTodoTask
import com.stanroy.todolist.data.db.dao.TasksDao
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl(private val tasksDao: TasksDao) : TodoRepository {
    override suspend fun getAllTodoTasks(): List<TodoTask> =
        tasksDao.getAll().map { it.toTodoTask() }

    override suspend fun getTaskById(id: Int): TodoTask = tasksDao.getById(id).toTodoTask()


    override suspend fun addNewTask(todoTask: TodoTask) {
        tasksDao.insert(todoTask.toTaskEntity())
    }

    override suspend fun deleteTask(todoTask: TodoTask) {
        tasksDao.delete(todoTask.toTaskEntity())
    }

    override suspend fun updateTask(todoTask: TodoTask) {
        tasksDao.updateTask(todoTask.toTaskEntity())
    }


}