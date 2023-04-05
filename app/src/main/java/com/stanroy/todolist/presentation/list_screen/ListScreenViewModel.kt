package com.stanroy.todolist.presentation.list_screen

import androidx.lifecycle.ViewModel
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListScreenViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {

    private val dbScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val _tasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        readTasksFromDatabase()
    }

    private fun readTasksFromDatabase() {
        dbScope.launch {
            _tasks.emit(repository.getAllTodoTasks())
        }
    }


}