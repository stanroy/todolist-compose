package com.stanroy.todolist.presentation.screen_add_task

import androidx.lifecycle.ViewModel
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository
import com.stanroy.todolist.presentation.common.ViewModelCommons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskScreenViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    fun addNewTask(title: String, description: String) {
        ViewModelCommons.dbScope.launch {
            val task = TodoTask(title = title, description = description)
            todoRepository.addNewTask(task)
        }
    }
}