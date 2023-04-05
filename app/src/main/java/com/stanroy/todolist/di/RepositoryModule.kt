package com.stanroy.todolist.di

import com.stanroy.todolist.data.db.dao.TasksDao
import com.stanroy.todolist.data.repository.TodoRepositoryImpl
import com.stanroy.todolist.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTodoRepository(tasksDao: TasksDao): TodoRepository = TodoRepositoryImpl(tasksDao)
}