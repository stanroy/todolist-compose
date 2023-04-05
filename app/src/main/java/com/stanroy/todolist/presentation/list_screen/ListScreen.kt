package com.stanroy.todolist.presentation.list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stanroy.todolist.R
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.presentation.common.RoundedCheckBox
import com.stanroy.todolist.presentation.common.windowHorizontalPadding
import com.stanroy.todolist.presentation.theme.TodoAppTypography

@Composable
fun ListScreen(navController: NavController, viewModel: ListScreenViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO Open New Task Screen, use navController*/ }) {
            Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "New task")
        }
    }) {
        LazyColumn(
            modifier = Modifier.padding(it.calculateBottomPadding()),
            contentPadding = PaddingValues(start = windowHorizontalPadding, end = windowHorizontalPadding, bottom = 64.dp)
        ) {

            /*  TODO remove later, for initial testing purposes only
                val tempList = mutableListOf<TodoTask>()

                for (i in 1..10) {
                    tempList.add(TodoTask("do smth $i", "desc $i"))
                }*/
            items(tasks) { task ->
                ListItem(task = task)
                Divider()
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
            Text(text = task.title, style = TodoAppTypography.BlackBoldTitleUiL)
            task.description?.let { Text(text = it, style = TodoAppTypography.GrayItalicTextBodyUiL) }
        }
        Image(
            modifier = Modifier.clickable(interactionSource = MutableInteractionSource(), indication = null) { /*TODO delete task*/ },
            painter = painterResource(id = R.drawable.delete),
            contentDescription = "Remove task",
            colorFilter = ColorFilter.tint(color = Color.Gray)
        )
    }
}

