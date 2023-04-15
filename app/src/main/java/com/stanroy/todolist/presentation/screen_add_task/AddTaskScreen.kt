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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stanroy.todolist.presentation.common.RoundedTextField
import com.stanroy.todolist.presentation.common.defaultWindowPadding
import com.stanroy.todolist.presentation.theme.TodoAppTypography
import com.stanroy.todolist.presentation.theme.TodolistTheme
import com.stanroy.todolist.presentation.theme.defaultRoundedCornerShape

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddTaskScreenViewModel = hiltViewModel()
) {
    var canSave by remember { mutableStateOf(false) }
    var titleValue by rememberSaveable { mutableStateOf("") }
    var descriptionValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize().defaultWindowPadding
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bgColor = MaterialTheme.colors.onSurface
        val bgModifier = Modifier.background(
            color = bgColor,
            shape = defaultRoundedCornerShape
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .then(bgModifier)
                .padding(32.dp),
            text = "AddTask",
            style = TodoAppTypography.screenTitle(MaterialTheme.colors.surface),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 48.dp)
                .then(bgModifier)
                .padding(vertical = 64.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundedTextField(
                fieldTitle = "title",
                value = titleValue,
                onValueChange = { newValue ->
                    titleValue = newValue
                    canSave = titleValue.isNotEmpty()
                },
                maxLines = 1,
                singleLine = true,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(24.dp))
            RoundedTextField(
                textFieldModifier = Modifier.fillMaxHeight(),
                fieldTitle = "description",
                value = descriptionValue,
                onValueChange = { newValue -> descriptionValue = newValue },
                maxLines = 10,
                singleLine = false,
                imeAction = ImeAction.Done
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 16.dp),
            contentPadding = PaddingValues(16.dp),
            shape = RoundedCornerShape(24.dp),
            onClick = {
                if (canSave) {
                    viewModel.addNewTask(titleValue, descriptionValue)
                    navController.popBackStack()
                }
            }) {
            AnimatedContent(targetState = canSave) { canSave ->
                val text = if (canSave) "Save" else "Fill all needed field"
                Text(text = text)
            }
        }
    }
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
        AddTaskScreen(rememberNavController(), hiltViewModel())
    }
}