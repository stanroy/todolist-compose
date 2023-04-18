package com.stanroy.todolist.presentation.screen_add_task

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
class AddTaskScreenViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    private val _state = mutableStateOf(AddTaskState())
    val state: State<AddTaskState> = _state

    private fun addTaskToDatabase(todoTask: TodoTask) {
        ViewModelCommons.dbScope.launch {
            todoRepository.addNewTask(todoTask)
        }
    }

    private fun getTaskFromDatabase(id: Int) {
        ViewModelCommons.dbScope.launch {
            val task = todoRepository.getTaskById(id)
            _state.value = AddTaskState(taskToEdit = task)
        }
    }

    fun addEditNewTask(todoTask: TodoTask) =
        addTaskToDatabase(todoTask)

    fun getTaskToEdit(id: Int) {
        if (id != -1) getTaskFromDatabase(id)
    }

}