package com.stanroy.todolist.data.db.dao

import androidx.room.*
import com.stanroy.todolist.data.db.entities.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)
}