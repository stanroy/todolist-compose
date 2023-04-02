package com.stanroy.todolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stanroy.todolist.data.db.dao.TasksDao
import com.stanroy.todolist.data.db.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}