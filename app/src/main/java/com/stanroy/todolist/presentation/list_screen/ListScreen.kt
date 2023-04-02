package com.stanroy.todolist.presentation.list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.stanroy.todolist.domain.TaskModel
import com.stanroy.todolist.presentation.common.Screen

@Composable
fun ListScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(it.calculateBottomPadding())) {
            val tempList = mutableListOf<TaskModel>()

            for (i in 1..10) {
                tempList.add(TaskModel("do smth $i"))
            }
            items(tempList) {task ->
                Text(text = task.title)
            }
        }
    }
}

