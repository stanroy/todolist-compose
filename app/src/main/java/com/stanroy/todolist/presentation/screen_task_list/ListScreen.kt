package com.stanroy.todolist.presentation.screen_task_list

import android.content.res.Configuration
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
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.stanroy.todolist.presentation.common.addEditTaskRoute
import com.stanroy.todolist.presentation.common.windowHorizontalPadding
import com.stanroy.todolist.presentation.common.windowVerticalPadding
import com.stanroy.todolist.presentation.theme.TodolistTheme
import kotlinx.coroutines.launch

@Composable
fun ListScreen(navController: NavController, viewModel: ListScreenViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val taskDeletedText = stringResource(id = R.string.list_screen_snackbar_task_deleted)
    val taskGoBackText = stringResource(id = R.string.list_screen_snackbar_go_back)

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllTasks()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().zIndex(1f),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { navController.navigate(Screen.AddTaskScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = stringResource(id = R.string.list_screen_new_task_cd)
                )
            }
        }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .zIndex(0f),
            contentPadding = PaddingValues(
                top = windowVerticalPadding,
                start = windowHorizontalPadding,
                end = windowHorizontalPadding,
                bottom = 64.dp
            )
        ) {
            items(state.tasks) { task ->
                ListItem(
                    task = task,
                    onEditTaskClicked = {
                        navController.navigate(
                            Screen.AddTaskScreen.addEditTaskRoute(
                                task.id.toString()
                            )
                        )
                    },
                    onTaskRemoveClicked = {
                        viewModel.deleteTask(task)
                        scope.launch {
                            val deleteTaskResult = scaffoldState.snackbarHostState.showSnackbar(
                                taskDeletedText,
                                taskGoBackText
                            )
                            if (deleteTaskResult == SnackbarResult.ActionPerformed) {
                                viewModel.restoreTask(task)
                            }
                        }
                    },
                    onStateChanged = { state ->
                        viewModel.updateTaskState(task.copy(isFinished = state))
                    })
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(0.2f),
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}


@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    task: TodoTask,
    onEditTaskClicked: () -> Unit,
    onTaskRemoveClicked: () -> Unit,
    onStateChanged: (Boolean) -> Unit
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
                state = task.isFinished,
                borderColor = MaterialTheme.colors.onPrimary,
                checkedBackground = MaterialTheme.colors.onPrimary,
                onStateChanged = onStateChanged
            )
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

            Row {
                Image(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable() { onEditTaskClicked() },
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = stringResource(id = R.string.list_screen_remove_task_cd),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary)
                )
                Image(
                    modifier = Modifier
                        .padding(end = 32.dp)
                        .clickable() { onTaskRemoveClicked() },
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = stringResource(id = R.string.list_screen_remove_task_cd),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary)
                )
            }
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
                ListItem(task = task, onEditTaskClicked = {}, onTaskRemoveClicked = {}) {}
                Divider()
            }
        }
    }
}

