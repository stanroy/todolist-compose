package com.stanroy.todolist.data.db.dao

import androidx.room.*
import com.stanroy.todolist.data.db.entities.TaskEntity

@Dao
interface TasksDao {

    @Query("SELECT * FROM TaskEntity")
    fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskEntity: TaskEntity)

    @Delete
    fun delete(taskEntity: TaskEntity)

    @Update
    fun updateTask(taskEntity: TaskEntity)
}