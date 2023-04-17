package com.stanroy.todolist.data.repository

import android.util.Log
import com.stanroy.todolist.data.common.toTaskEntity
import com.stanroy.todolist.data.common.toTodoTask
import com.stanroy.todolist.data.db.dao.TasksDao
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl(private val tasksDao: TasksDao) : TodoRepository {
    override suspend fun getAllTodoTasks(): List<TodoTask> =
        tasksDao.getAll().map { it.toTodoTask() }

    override suspend fun addNewTask(todoTask: TodoTask) {
        tasksDao.insert(todoTask.toTaskEntity())
        Log.d("TAG_TAG", "new task: ${todoTask.toTaskEntity()}")
    }

    override suspend fun deleteTask(todoTask: TodoTask) {
        Log.d("TAG_TAG", "delete task: $todoTask")
        tasksDao.delete(todoTask.toTaskEntity())
    }


}