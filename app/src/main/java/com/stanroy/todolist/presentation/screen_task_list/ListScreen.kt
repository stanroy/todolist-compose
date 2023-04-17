package com.stanroy.todolist.presentation.screen_task_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stanroy.todolist.R
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.presentation.common.RoundedCheckBox
import com.stanroy.todolist.presentation.common.Screen
import com.stanroy.todolist.presentation.common.windowHorizontalPadding

@Composable
fun ListScreen(navController: NavController, viewModel: ListScreenViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getAllTasks()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.zIndex(1f),
            backgroundColor = MaterialTheme.colors.primary,
            onClick = { navController.navigate(Screen.AddTaskScreen.route) }) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "New task"
            )
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .padding(it.calculateBottomPadding())
                .zIndex(1f),
            contentPadding = PaddingValues(
                start = windowHorizontalPadding,
                end = windowHorizontalPadding,
                bottom = 64.dp
            )
        ) {
            items(state.tasks) { task ->
                ListItem(task = task)
                Divider()
            }
        }

        if (state.isLoading) {
            val circularProgressBg = Color(0x68D1D1D1)
            Box(
                modifier = Modifier
                    .background(color = circularProgressBg)
                    .fillMaxSize()
                    .zIndex(2f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(0.2f),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}


@Composable
fun ListItem(modifier: Modifier = Modifier, task: TodoTask) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        RoundedCheckBox(onStateChanged = {/*TODO change task state*/ })
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground
            )
            task.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
        Image(
            modifier = Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { /*TODO delete task*/ },
            painter = painterResource(id = R.drawable.delete),
            contentDescription = "Remove task",
            colorFilter = ColorFilter.tint(color = Color.Gray)
        )
    }
}

