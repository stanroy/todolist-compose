package com.stanroy.todolist.presentation.screen_task_list

import androidx.lifecycle.ViewModel
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository
import com.stanroy.todolist.presentation.common.ViewModelCommons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListScreenViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {

    private val _tasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val tasks = _tasks.asStateFlow()

    private fun readTasksFromDatabase() {
        ViewModelCommons.dbScope.launch {
            _tasks.emit(repository.getAllTodoTasks())
        }
    }

    fun getAllTasks() = readTasksFromDatabase()


}