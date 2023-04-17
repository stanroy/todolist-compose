package com.stanroy.todolist.presentation.screen_task_list

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stanroy.todolist.R
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.presentation.common.RoundedCheckBox
import com.stanroy.todolist.presentation.common.Screen
import com.stanroy.todolist.presentation.common.windowHorizontalPadding
import com.stanroy.todolist.presentation.common.windowVerticalPadding
import com.stanroy.todolist.presentation.theme.TodolistTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(navController: NavController, viewModel: ListScreenViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getAllTasks()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier
                .zIndex(1f),
            backgroundColor = MaterialTheme.colors.primary,
            onClick = { navController.navigate(Screen.AddTaskScreen.route) }) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "New task"
            )
        }
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .zIndex(1f),
            contentPadding = PaddingValues(
                top = windowVerticalPadding,
                start = windowHorizontalPadding,
                end = windowHorizontalPadding,
                bottom = 64.dp
            )
        ) {
            items(state.tasks) { task ->
                val canShowTask = remember { MutableTransitionState(false) }
                    .apply { targetState = true }

                AnimatedVisibility(
                    visibleState = canShowTask,
                    enter = fadeIn(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    ListItem(modifier = Modifier.animateItemPlacement(), task = task) {
                        canShowTask.targetState = false
                        viewModel.deleteTask(task)
                    }
                }

                if (!canShowTask.currentState) {
                    viewModel.getAllTasks()
                }

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
fun ListItem(
    modifier: Modifier = Modifier,
    task: TodoTask,
    onTaskRemoveClicked: () -> Unit
) {
    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.0f to MaterialTheme.colors.primary,
            1f to MaterialTheme.colors.primaryVariant
        )
    )
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(brush = gradient, shape = RoundedCornerShape(24.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedCheckBox(
                modifier = Modifier.padding(start = 32.dp),
                borderColor = MaterialTheme.colors.onPrimary,
                checkedBackground = MaterialTheme.colors.onPrimary,
                onStateChanged = {/*TODO change task state*/ })
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(32.dp)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onPrimary
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
                modifier = Modifier
                    .padding(end = 32.dp)
                    .clickable() { onTaskRemoveClicked() },
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Remove task",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(
    name = "TaskList",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    device = Devices.PIXEL_2
)
@Composable
fun DefaultPreview() {
    val tempList = mutableListOf<TodoTask>()
    for (i in 1..10) {
        tempList.add(TodoTask(title = "do smth $i", description = "desc $i"))
    }

    TodolistTheme {
        LazyColumn(
            contentPadding = PaddingValues(
                start = windowHorizontalPadding,
                end = windowHorizontalPadding,
                bottom = 64.dp
            )
        ) {
            items(tempList) { task ->
                ListItem(task = task) {

                }
                Divider()
            }
        }
    }
}

