package com.stanroy.todolist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "task_title") val title: String,
    @ColumnInfo(name = "task_description") val description: String?,
    @ColumnInfo(name = "is_finished") val isFinished: Boolean = false
)
