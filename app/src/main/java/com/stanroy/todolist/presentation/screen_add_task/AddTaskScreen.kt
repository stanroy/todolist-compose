package com.stanroy.todolist.presentation.screen_add_task

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stanroy.todolist.R
import com.stanroy.todolist.domain.model.TodoTask
import com.stanroy.todolist.presentation.common.RoundedTextField
import com.stanroy.todolist.presentation.common.defaultWindowPadding
import com.stanroy.todolist.presentation.theme.TodolistTheme
import com.stanroy.todolist.presentation.theme.defaultRoundedCornerShape

@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddTaskScreenViewModel = hiltViewModel(),
    taskIdToEdit: Int?,
) {

    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        taskIdToEdit?.let { viewModel.getTaskToEdit(taskIdToEdit.toInt()) }
    }

    NewTaskForm(taskToEdit = state.taskToEdit, addNewTask = { task ->
        viewModel.addEditNewTask(task)
        navController.popBackStack()
    })
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewTaskForm(
    modifier: Modifier = Modifier,
    taskToEdit: TodoTask?,
    addNewTask: (task: TodoTask) -> Unit,
) {
    var canSave by remember { mutableStateOf(false) }
    var titleValue by rememberSaveable { mutableStateOf("") }
    var descriptionValue by rememberSaveable { mutableStateOf("") }
    val bgColor = MaterialTheme.colors.primary
    val bgModifier = Modifier.background(
        color = bgColor,
        shape = defaultRoundedCornerShape
    )

    LaunchedEffect(key1 = taskToEdit) {
        taskToEdit?.let {
            titleValue = it.title
            descriptionValue = it.description.orEmpty()
            canSave = true
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxSize().defaultWindowPadding,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .then(bgModifier)
                .padding(32.dp),
            text = stringResource(id = R.string.add_task_screen_title),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .then(bgModifier)
                .padding(vertical = 32.dp)
        ) {
            RoundedTextField(
                fieldTitle = stringResource(id = R.string.add_task_title_hint).lowercase(),
                value = titleValue,
                onValueChange = { newValue ->
                    titleValue = newValue
                    canSave = titleValue.isNotEmpty()
                },
                maxLines = 1,
                singleLine = true,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(32.dp))
            RoundedTextField(
                textFieldModifier = Modifier
                    .heightIn(max = 256.dp)
                    .fillMaxHeight(),
                fieldTitle = stringResource(id = R.string.add_task_description_hint).lowercase(),
                value = descriptionValue,
                onValueChange = { newValue -> descriptionValue = newValue },
                maxLines = 10,
                singleLine = false,
                imeAction = ImeAction.Done
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .heightIn(64.dp)
                .fillMaxWidth(0.7f)
                .padding(bottom = 16.dp),
            contentPadding = PaddingValues(16.dp),
            shape = RoundedCornerShape(24.dp),
            onClick = {
                if (canSave) createEditTask(taskToEdit, titleValue, descriptionValue) {
                    addNewTask(it)
                }
            }) {
            AnimatedContent(targetState = canSave) { canSave ->
                val text =
                    if (canSave) stringResource(id = R.string.add_task_save_button_label) else stringResource(
                        id = R.string.add_task_save_button_fill_title_label
                    )
                Text(
                    text = text,
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }

    }
}

fun createEditTask(
    taskToEdit: TodoTask?,
    titleValue: String,
    descriptionValue: String,
    createTask: (TodoTask) -> Unit
) {
    val newTask =
        TodoTask(title = titleValue, description = descriptionValue)
    taskToEdit?.let {
        createTask(
            it.copy(
                title = titleValue,
                description = descriptionValue
            )
        )
    } ?: createTask(newTask)
}


@Preview(
    name = "AddTask",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    device = Devices.PIXEL_2
)
@Composable
fun DefaultPreview() {
    TodolistTheme {
        NewTaskForm(taskToEdit = null, addNewTask = { _ ->

        })
    }
}