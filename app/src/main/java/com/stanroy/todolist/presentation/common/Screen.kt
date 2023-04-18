package com.stanroy.todolist.presentation.common

sealed class Screen(val route: String) {
    object ListScreen : Screen("ListScreen")
    object AddTaskScreen : Screen("AddTaskScreen")
}

val Screen.AddTaskScreen.taskId
    get() = "taskId"

fun Screen.AddTaskScreen.addEditTaskRoute(taskId: String = "{taskId}") = "AddTaskScreen?taskId=$taskId"