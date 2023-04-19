package com.stanroy.todolist

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.stanroy.todolist.data.db.TasksDatabase
import com.stanroy.todolist.data.db.dao.TasksDao
import com.stanroy.todolist.data.db.entities.TaskEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TasksDaoTest {

    private lateinit var tasksDb: TasksDatabase
    private lateinit var tasksDao: TasksDao

    @Before
    fun setUp() {
        tasksDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TasksDatabase::class.java
        ).allowMainThreadQueries().build()

        tasksDao = tasksDb.tasksDao()
    }

    @Test
    fun when_insert_expect_newDbEntry() {
        val task = TaskEntity(uid = 24, title = "Get groceries", description = "i need me some apples")
        tasksDao.insert(task)
        val taskById = tasksDao.getById(task.uid)
        val allTasks = tasksDao.getAll()
        assertThat(allTasks).hasSize(1)
        assertThat(taskById).isEqualTo(task)
    }


    @Test
    fun when_deleteOneOfTwoEntries_expect_oneEntryLeft() {
        val task = TaskEntity(uid = 24, title = "Get groceries", description = "i need me some apples")
        val secondTask =
            TaskEntity(uid = 8, title = "Water mah plants", description = "")

        tasksDao.insert(task)
        tasksDao.insert(secondTask)

        val allTasks = tasksDao.getAll()
        assertThat(allTasks).hasSize(2)
        tasksDao.delete(task)

        val tasksAfterDelete = tasksDao.getAll()
        assertThat(tasksAfterDelete).hasSize(1)
    }

    @Test
    fun when_updateExistingEntry_expect_entryHasUpdatedValues() {
        val task = TaskEntity(uid = 24, title = "Get groceries", description = "i need me some apples")

        tasksDao.insert(task)

        tasksDao.updateTask(task.copy(isFinished = true))
        val updatedTaskById = tasksDao.getById(task.uid)
        assertThat(updatedTaskById.isFinished).isEqualTo(true)
    }

    @After
    fun tearDown() {
        tasksDb.close()
    }

}