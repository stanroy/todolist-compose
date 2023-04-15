package com.stanroy.todolist.presentation.screen_add_task

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stanroy.todolist.presentation.theme.TodoAppTypography
import com.stanroy.todolist.presentation.theme.TodolistTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddTaskScreen() {
    var canSave by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 16.dp),
                contentPadding = PaddingValues(16.dp),
                shape = RoundedCornerShape(24.dp),
                onClick = { /*TODO Add New Task, check $canSave */ }) {
                AnimatedContent(targetState = canSave) { canSave ->
                    val text = if (canSave) "Save" else "Fill all needed field"
                    Text(text = text)
                }
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .padding(it.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "AddTask",
                style = TodoAppTypography.taskTitle(MaterialTheme.colors.onSurface)
            )
        }
    }
}

@Preview(
    name = "AddTask",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreview() {
    TodolistTheme {
        AddTaskScreen()
    }
}