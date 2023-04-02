package com.stanroy.todolist.presentation.common

sealed class Screen(val route: String) {
    object ListScreen : Screen("ListScreen")
}
