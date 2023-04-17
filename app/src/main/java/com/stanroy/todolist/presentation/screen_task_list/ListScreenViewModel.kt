package com.stanroy.todolist.presentation.screen_task_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.domain.repository.TodoRepository
import com.stanroy.todolist.presentation.common.ViewModelCommons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListScreenViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {

    private val _state = mutableStateOf(ListScreenState())
    val state: State<ListScreenState> = _state

    private fun readTasksFromDatabase() {
        ViewModelCommons.dbScope.launch {
            _state.value = ListScreenState(isLoading = true)
            val tasks = repository.getAllTodoTasks()
            _state.value = ListScreenState(isLoading = false, tasks = tasks)
        }
    }

    private fun deleteTaskFromDatabase(todoTask: TodoTask) {
        ViewModelCommons.dbScope.launch {
            repository.deleteTask(todoTask)
        }
    }


    fun getAllTasks() = readTasksFromDatabase()
    fun deleteTask(todoTask: TodoTask) = deleteTaskFromDatabase(todoTask)


}