package com.stanroy.todolist.domain.repository

import com.stanroy.todolist.domain.model.TodoTask

interface TodoRepository {


    suspend fun getAllTodoTasks(): List<TodoTask>

    suspend fun getTaskById(id: Int): TodoTask

    suspend fun addNewTask(todoTask: TodoTask)

    suspend fun deleteTask(todoTask: TodoTask)

    suspend fun updateTask(todoTask: TodoTask)

}