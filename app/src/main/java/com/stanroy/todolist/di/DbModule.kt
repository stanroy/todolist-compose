package com.stanroy.todolist.di

import android.content.Context
import androidx.room.Room
import com.stanroy.todolist.data.db.TasksDatabase
import com.stanroy.todolist.data.db.dao.TasksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideTasksDatabase(@ApplicationContext applicationContext: Context): TasksDatabase =
        Room.databaseBuilder(
            applicationContext,
            TasksDatabase::class.java,
            "tasks-db"
        ).build()

    @Provides
    @Singleton
    fun provideTasksDao(tasksDatabase: TasksDatabase): TasksDao = tasksDatabase.tasksDao()
}